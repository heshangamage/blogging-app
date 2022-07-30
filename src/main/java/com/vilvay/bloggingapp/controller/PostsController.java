package com.vilvay.bloggingapp.controller;

import com.vilvay.bloggingapp.dtos.CommentsDTO;
import com.vilvay.bloggingapp.dtos.PostsDTO;
import com.vilvay.bloggingapp.exception.ResourceNotFoundException;
import com.vilvay.bloggingapp.service.CommentService;
import com.vilvay.bloggingapp.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Purpose : This controller will handle all the comments
 * related Rest API operations
 * */
@RestController
@RequestMapping("/v1/posts")
public class PostsController {

    private final PostsService postsService;

    private final CommentService commentService;

    @Autowired
    public PostsController(PostsService postsService, CommentService commentService) {
        this.postsService = postsService;
        this.commentService = commentService;
    }

    /**
     * POST API to create posts
     * @param postsDTORequest request post object
     * @return created post object
     * */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostsDTO> createPost(@RequestBody PostsDTO postsDTORequest) {
        return new ResponseEntity<>(postsService.savePosts(postsDTORequest), HttpStatus.CREATED);
    }

    /**
     * GET API to retrieve post by id
     * @param postId post id
     * @return post object as response
     * */
    @GetMapping("/{postId}")
    public ResponseEntity<PostsDTO> findPost(@PathVariable("postId") int postId) {
        return new ResponseEntity<>(postsService.findPostById(postId), HttpStatus.OK);
    }

    /**
     * GET API to retrieve comments by post id
     *
     * @param postId post id
     * @return list of comments relevant to given post id
     * */
    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<CommentsDTO>> findAllCommentsByPostId(@PathVariable("postId") int postId) {
        return new ResponseEntity<>(commentService.getAllCommentsByPostId(postId), HttpStatus.OK);
    }

    /**
     * PUT API to update  posts
     *
     * @param postId post id
     * @param request update post object values
     * @return post object with updated details
     * @throws ResourceNotFoundException
     * */
    @PutMapping(value = "/{postId}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostsDTO> updatePost(@PathVariable("postId") int postId,
                                                  @RequestBody PostsDTO request) throws ResourceNotFoundException {
        return new ResponseEntity<>(postsService.updatePostById(postId, request), HttpStatus.OK);
    }

    /**
     * DELETE API for delete posts
     *
     * @param postId post id
     * @return http status OK
     * @throws ResourceNotFoundException
     * */
    @DeleteMapping("/{postId}")
    public ResponseEntity<HttpStatus> deletePost(@PathVariable("postId") int postId)
            throws ResourceNotFoundException {
        postsService.deletePostById(postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
