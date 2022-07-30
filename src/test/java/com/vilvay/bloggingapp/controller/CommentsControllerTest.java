package com.vilvay.bloggingapp.controller;

import com.vilvay.bloggingapp.dtos.CommentsDTO;
import com.vilvay.bloggingapp.exception.ResourceNotFoundException;
import com.vilvay.bloggingapp.service.CommentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentsControllerTest {

    private static final int COMMENT_ID = 2;
    @Mock
    private CommentService commentService;

    @InjectMocks
    private CommentsController commentsController;


    @Test
    void testCreateComment() {
        CommentsDTO commentsDTO = new CommentsDTO();
        when(commentService.createComment(any(CommentsDTO.class)))
                .thenReturn(new CommentsDTO());

        ResponseEntity<CommentsDTO> response = commentsController.createComment(commentsDTO);

        verify(commentService, times(1))
                .createComment(any(CommentsDTO.class));
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void testFindComments() {
        when(commentService.findCommentById(COMMENT_ID))
                .thenReturn(new CommentsDTO());

        ResponseEntity<CommentsDTO> response = commentsController.findComment(COMMENT_ID);

        verify(commentService, times(1))
                .findCommentById(COMMENT_ID);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testUpdateComments() throws ResourceNotFoundException {
        CommentsDTO commentsDTO = new CommentsDTO();
        when(commentService.updateCommentById(anyInt(), any(CommentsDTO.class)))
                .thenReturn(new CommentsDTO());

        ResponseEntity<CommentsDTO> response = commentsController.updateComment(COMMENT_ID, commentsDTO);

        verify(commentService, times(1))
                .updateCommentById(COMMENT_ID, commentsDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testDeleteComments() throws ResourceNotFoundException {
        ResponseEntity<HttpStatus> response = commentsController.deleteComment(COMMENT_ID);

        verify(commentService, times(1))
                .deleteCommentById(COMMENT_ID);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}