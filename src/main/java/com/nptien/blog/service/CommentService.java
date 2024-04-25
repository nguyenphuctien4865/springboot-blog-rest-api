package com.nptien.blog.service;

import java.util.List;

import com.nptien.blog.dtos.CommentDto;

public interface CommentService {
    CommentDto createComment(Long postId, CommentDto commentDto);

    List<CommentDto> getCommentsByPostId(Long postId);

    CommentDto getCommentById(Long id, Long postId);

    CommentDto updateComment(Long postId, Long commentId, CommentDto commentRequest);

    void deleteComment(Long postId, Long commentId);
}
