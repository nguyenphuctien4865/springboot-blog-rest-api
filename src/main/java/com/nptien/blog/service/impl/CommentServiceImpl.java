package com.nptien.blog.service.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.tomcat.util.log.UserDataHelper.Mode;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.nptien.blog.dtos.CommentDto;
import com.nptien.blog.entity.Comment;
import com.nptien.blog.entity.Post;
import com.nptien.blog.exception.BlogAPIException;
import com.nptien.blog.exception.ResourceNotFoundExceptionnn;
import com.nptien.blog.repository.CommentRepository;
import com.nptien.blog.repository.PostRepository;
import com.nptien.blog.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    private ModelMapper modelMapper;
    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public CommentServiceImpl(ModelMapper modelMapper, CommentRepository commentRepository,
            PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDto createComment(Long postId, CommentDto commentDto) {

        Comment comment = mapToEntity(commentDto);

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundExceptionnn("Post", "Id", postId));

        comment.setPost(post);

        Comment newComment = commentRepository.save(comment);

        return mapToDto(newComment);

    }

    @Override
    public List<CommentDto> getCommentsByPostId(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());

        // return comments.stream().map(
        // comment -> mapToDto(comment))
        // ..collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long id, Long postId) {
        // TODO Auto-generated method stub
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundExceptionnn("Post", "Id", postId));

        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundExceptionnn("Comment", "Id", id));

        if (comment.getPost().getId() != post.getId()) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }
        return mapToDto(comment);
    }

    @Override
    public CommentDto updateComment(Long postId, Long commentId, CommentDto commentRequest) {
        // TODO Auto-generated method stub
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundExceptionnn("Post", "Id", postId));

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundExceptionnn("Comment", "id", commentId));
        if (comment.getPost().getId() != post.getId()) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

        comment.setName(commentRequest.getName());
        comment.setEmail(commentRequest.getEmail());
        comment.setBody(commentRequest.getBody());
        return mapToDto(commentRepository.save(comment));

    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundExceptionnn("Post", "Id", postId));

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundExceptionnn("Comment", "id", commentId));
        if (comment.getPost().getId() != post.getId()) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

        commentRepository.delete(comment);

    }

    private CommentDto mapToDto(Comment comment) {

        // CommentDto commentDto = new CommentDto();
        // commentDto.setId(comment.getId());
        // commentDto.setName(comment.getName());
        // commentDto.setEmail(comment.getEmail());
        // commentDto.setBody(comment.getBody());
        CommentDto commentDto = modelMapper.map(comment, CommentDto.class);
        return commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto) {

        // Comment comment = new Comment();
        // comment.setId(commentDto.getId());
        // comment.setName(commentDto.getName());
        // comment.setEmail(commentDto.getEmail());
        // comment.setBody(commentDto.getBody());
        Comment comment = modelMapper.map(commentDto, Comment.class);
        return comment;
    }

}
