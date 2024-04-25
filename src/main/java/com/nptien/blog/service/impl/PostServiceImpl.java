package com.nptien.blog.service.impl;

import com.nptien.blog.dtos.PostDto;
import com.nptien.blog.dtos.PostRespone;
import com.nptien.blog.entity.Post;
import com.nptien.blog.exception.ResourceNotFoundExceptionnn;
import com.nptien.blog.repository.PostRepository;
import com.nptien.blog.service.PostService;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Service
public class PostServiceImpl implements PostService {

    private ModelMapper modelMapper;

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        // convert DTO to Entity
        Post post = mapToEntity(postDto);

        Post newPost = postRepository.save(post);

        return mapToDto(newPost);
    }

    @Override
    public PostRespone getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // Create Pageable object
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Post> posts = postRepository.findAll(pageable);

        // Get content for page object
        List<Post> postList = posts.getContent();

        List<PostDto> content = postList.stream().map(this::mapToDto).collect(Collectors.toList());

        PostRespone postRespone = new PostRespone();
        postRespone.setContent(content);
        postRespone.setTotalPages(posts.getTotalPages());
        postRespone.setPageSize(pageSize);
        postRespone.setPageNo(pageNo);
        postRespone.setTotalElements(posts.getTotalElements());
        postRespone.setLast(posts.isLast());

        return postRespone;
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundExceptionnn("Post", "id", id));
        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(Long id, PostDto postDto) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundExceptionnn("Post", "id", id));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post updatedPost = postRepository.save(post);
        return mapToDto(updatedPost);
    }

    @Override
    public void deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundExceptionnn("Post", "id", id));
        postRepository.delete(post);
    }

    private PostDto mapToDto(Post post) {
        // PostDto postDto = new PostDto();
        // postDto.setId(post.getId());
        // postDto.setTitle(post.getTitle());
        // postDto.setDescription(post.getDescription());
        // postDto.setContent(post.getContent());

        return modelMapper.map(post, PostDto.class);
    }

    private Post mapToEntity(PostDto postDto) {
        // Post post = new Post();
        // post.setId(postDto.getId());
        // post.setTitle(postDto.getTitle());
        // post.setDescription(postDto.getDescription());
        // post.setContent(postDto.getContent());
        return modelMapper.map(postDto, Post.class);
    }

}
