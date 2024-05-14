package com.example.StudyWithMe.services.socialmedia.comment;

import com.example.StudyWithMe.dataTransferObjects.socialmedia.comment.CommentDTO;
import com.example.StudyWithMe.exceptions.DataNotFoundException;
import com.example.StudyWithMe.models.socialmedia.comment.Comment;
import com.example.StudyWithMe.models.socialmedia.comment.CommentAttachment;
import com.example.StudyWithMe.models.socialmedia.post.Post;
import com.example.StudyWithMe.models.user.auth.User;
import com.example.StudyWithMe.models.user.profile.Profile;
import com.example.StudyWithMe.repositories.socialmedia.comment.CommentAttachmentRepository;
import com.example.StudyWithMe.repositories.socialmedia.comment.CommentRepository;
import com.example.StudyWithMe.responses.socialmedia.comment.CommentResponse;
import com.example.StudyWithMe.responses.socialmedia.comment.ListCommentResponse;
import com.example.StudyWithMe.services.attachment.IAttachmentService;
import com.example.StudyWithMe.services.user.auth.IAuthService;
import com.example.StudyWithMe.services.user.profile.IUserService;
import com.example.StudyWithMe.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentService implements ICommentService{
    private final CommentRepository commentRepository;
    private final CommentAttachmentRepository commentAttachmentRepository;
    private final IAttachmentService attachmentService;
    private final IAuthService authService;
    private final ICommentReplySerivce commentReplySerivce;
    private final IUserService userService;
    @Transactional
    @Override
    public CommentResponse createComment(Post post,CommentDTO commentDTO) {
        User user = authService.getCurrentUser();
        Comment newComment = Comment.builder()
                .user(user)
                .post(post)
                .content(commentDTO.getContent())
                .build();
        commentRepository.save(newComment);
        List<CommentAttachment> commentAttachments = new ArrayList<>();
        if (commentDTO.getAttachments() != null && !commentDTO.getAttachments().isEmpty()){
            commentAttachments = commentDTO.getAttachments().stream().map(attachment -> {
                String attachmentUrl = attachmentService.upload(attachment);
                CommentAttachment  commentAttachment = CommentAttachment.builder()
                        .comment(newComment)
                        .attachmentType(Utils.getFileType(attachment.getContentType()))
                        .attachmentUrl(attachmentUrl)
                        .build();
                commentAttachmentRepository.save(commentAttachment);
                return commentAttachment;
            }).collect(Collectors.toList());
        }
        Profile profileCard = userService.getProfile(user.getUserId());
        return CommentResponse.fromComment(user,profileCard,newComment,commentAttachments);
    }

    @Override
    public void deleteComment(Long commentId) {
        Comment existingComment = commentRepository.findById(commentId)
                .orElseThrow(()->new DataNotFoundException("Cannot found comment"));
        commentAttachmentRepository.deleteAllAttachmentForComment(commentId);
        commentReplySerivce.deleteAllReplyCommentForComment(commentId);
        commentRepository.delete(existingComment);
    }

    @Override
    public void deleteAllCommentForPost(Post post) {
        List<Comment> comments = commentRepository.findAllByPostId(post.getPostId());
        comments.stream().map(comment -> {
            commentAttachmentRepository.deleteAllAttachmentForComment(comment.getCommentId());
            commentReplySerivce.deleteAllReplyCommentForComment(comment.getCommentId());
            return null;
        }).collect(Collectors.toList());
        commentRepository.deleteAll(comments);
    }

    @Override
    public ListCommentResponse getAllCommentForPost(Long postId,int page,int limit) {
        PageRequest pageRequest = PageRequest.of(0, 10,
                Sort.by(Sort.Direction.DESC, "createdAt"));
        List<Comment> comments = commentRepository.findAllByPostId(postId,pageRequest);
        List<CommentResponse> commentResponses = comments
                .stream().map(comment -> {
                    List<CommentAttachment> commentAttachments = commentAttachmentRepository.findAllByCommentId(comment.getCommentId());
                    User user = authService.getUserByUserId(comment.getUser().getUserId());
                    Profile profileCard = userService.getProfile(user.getUserId());
                    return CommentResponse.fromComment(user,profileCard,comment,commentAttachments);
                }).collect(Collectors.toList());
        return ListCommentResponse.builder()
                .totalPages(10)
                .comments(commentResponses)
                .build();
    }
    @Override
    public Comment getComment(Long commentId){
        return commentRepository.findById(commentId)
                .orElseThrow(()->new DataNotFoundException("Cannot found comment!"));
    }

    @Override
    public long countByPostId(Long postId) {
        return commentRepository.countByPostId(postId);
    }
}
