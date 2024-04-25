package com.nptien.blog.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nptien.blog.dtos.PostDto;
import com.nptien.blog.dtos.PostRespone;
import com.nptien.blog.service.PostService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1/posts/")
public class PostController {

    @Autowired
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    // @GetMapping
    // public ResponseEntity<List<PostDto>> getAllPosts(
    // @RequestParam(value = "pageNo", defaultValue = "0", required = false) int
    // pageNo,
    // @RequestParam(value = "pageSize", defaultValue = "10", required = false) int
    // pageSize) {
    // return new ResponseEntity<>(postService.getAllPosts(pageNo, pageSize),
    // HttpStatus.OK);
    // }

    @GetMapping
    public PostRespone getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sort", defaultValue = "id", required = false) String sort,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {

        return postService.getAllPosts(pageNo, pageSize, sort, sortDir);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long id) {
        return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable Long id, @RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.updatePost(id, postDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return new ResponseEntity<>("Post entity deleted successfully  ", HttpStatus.OK);
    }
}
