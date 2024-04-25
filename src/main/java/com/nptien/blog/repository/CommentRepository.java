package com.nptien.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nptien.blog.entity.Comment;
import com.nptien.blog.entity.Post;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId);
}
