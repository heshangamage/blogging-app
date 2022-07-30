package com.vilvay.bloggingapp.controller;

import com.vilvay.bloggingapp.dtos.CommentsDTO;
import com.vilvay.bloggingapp.dtos.PostsDTO;
import com.vilvay.bloggingapp.exception.ResourceNotFoundException;
import com.vilvay.bloggingapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/comments")
public class CommentsController {

    private final CommentService commentService;

    @Autowired
    public CommentsController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommentsDTO> createComment(@RequestBody CommentsDTO commentsDTORequest) {
        return new ResponseEntity<>(commentService.createComment(commentsDTORequest), HttpStatus.CREATED);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentsDTO> findComment(@PathVariable("commentId") int commentId) {
        return new ResponseEntity<>(commentService.findCommentById(commentId), HttpStatus.OK);
    }

    @PutMapping(value = "/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommentsDTO> updateComment(@PathVariable("commentId") int commentId,
                                               @RequestBody CommentsDTO request) throws ResourceNotFoundException {
        return new ResponseEntity<>(commentService.updateCommentById(commentId, request), HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable("commentId") int commentId)
            throws ResourceNotFoundException {
        commentService.deleteCommentById(commentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
