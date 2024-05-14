package com.example.StudyWithMe.services.socialmedia.comment;

import com.example.StudyWithMe.dataTransferObjects.socialmedia.comment.ReplyCommentDTO;
import com.example.StudyWithMe.models.socialmedia.comment.Comment;
import com.example.StudyWithMe.models.socialmedia.comment.ReplyComment;
import com.example.StudyWithMe.models.socialmedia.comment.ReplyCommentAttachment;
import com.example.StudyWithMe.models.user.auth.User;
import com.example.StudyWithMe.models.user.profile.Profile;
import com.example.StudyWithMe.repositories.socialmedia.comment.ReplyCommentAttachmentRepository;
import com.example.StudyWithMe.repositories.socialmedia.comment.ReplyCommentRepository;
import com.example.StudyWithMe.responses.socialmedia.comment.replyComment.ListReplyCommentResponse;
import com.example.StudyWithMe.responses.socialmedia.comment.replyComment.ReplyCommentResponse;
import com.example.StudyWithMe.services.attachment.IAttachmentService;
import com.example.StudyWithMe.services.user.auth.IAuthService;
import com.example.StudyWithMe.services.user.profile.IUserService;
import com.example.StudyWithMe.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class CommentReplyComment implements ICommentReplySerivce{
    private final IAuthService authService;
    private final IAttachmentService attachmentService;
    private final ReplyCommentRepository replyCommentRepository;
    private final ReplyCommentAttachmentRepository replyCommentAttachmentRepository;
    private final IUserService userService;
    @Transactional
    @Override
    public ReplyCommentResponse createReplyComment(Comment comment,ReplyCommentDTO replyCommentDTO) {
        User existingUser = authService.getCurrentUser();
        ReplyComment replyComment = ReplyComment.builder()
                .comment(comment)
                .user(existingUser)
                .content(replyCommentDTO.getContent())
                .build();
        replyCommentRepository.save(replyComment);
        List<ReplyCommentAttachment> replyCommentAttachments = new ArrayList<>();
        if (replyCommentDTO.getAttachments() != null && !replyCommentDTO.getAttachments().isEmpty()) {
            replyCommentDTO.getAttachments().stream().map(attachment -> {
                String attachmentUrl = attachmentService.upload(attachment);
                ReplyCommentAttachment replyCommentAttachment = ReplyCommentAttachment.builder()
                        .attachmentType(Utils.getFileType(attachment.getContentType()))
                        .attachmentUrl(attachmentUrl)
                        .replyComment(replyComment)
                        .build();
                replyCommentAttachments.add(replyCommentAttachment);
                return replyCommentAttachmentRepository.save(replyCommentAttachment);
            }).collect(Collectors.toList());
        }
        Profile profile = userService.getProfile(existingUser.getUserId());
        return ReplyCommentResponse.fromReplyComment(existingUser,profile,replyComment,replyCommentAttachments);
    }

    @Override
    public void deleteReplyComment(Long replyCommentId) {
        replyCommentRepository.deleteById(replyCommentId);
    }

    @Override
    public void deleteAllReplyCommentForComment(Long commentId) {
        replyCommentRepository.deleteAllReplyForComment(commentId);
    }

    @Override
    public ListReplyCommentResponse getAllReplyCommentByComment(Long commentId) {
        List<ReplyComment> replyComments = replyCommentRepository.findAllByCommentId(commentId);
        List<ReplyCommentResponse> replyCommentResponses = replyComments.stream().map(replyComment -> {
            List<ReplyCommentAttachment> replyCommentAttachments = replyCommentAttachmentRepository.findAllByReplyCommentId(replyComment.getReplyCommentId());
            User user = authService.getUserByUserId(replyComment.getUser().getUserId());
            Profile profile = userService.getProfile(user.getUserId());
            return ReplyCommentResponse.fromReplyComment(user,profile,replyComment,replyCommentAttachments);
        }).collect(Collectors.toList());
        return ListReplyCommentResponse.builder()
                .totalPages(10L)
                .replyComments(replyCommentResponses)
                .build();
    }
}
