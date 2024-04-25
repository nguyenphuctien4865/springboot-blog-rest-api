package com.nptien.blog.service;

import java.util.List;

import com.nptien.blog.dtos.PostDto;
import com.nptien.blog.dtos.PostRespone;


public interface PostService {
    PostDto createPost(PostDto postDto);

    PostRespone getAllPosts(int pageNo, int pageSize, String sort, String sortDir);

    PostDto getPostById(Long id);

    PostDto updatePost(Long id, PostDto postDto);

    void deletePost(Long id);
}
