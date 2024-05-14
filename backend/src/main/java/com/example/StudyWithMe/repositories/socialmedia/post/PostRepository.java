package com.example.StudyWithMe.repositories.socialmedia.post;

import com.example.StudyWithMe.models.socialmedia.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
