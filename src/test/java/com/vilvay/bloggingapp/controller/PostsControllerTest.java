package com.vilvay.bloggingapp.controller;

import com.vilvay.bloggingapp.dtos.AuthorDTO;
import com.vilvay.bloggingapp.dtos.PostsDTO;
import com.vilvay.bloggingapp.exception.ResourceNotFoundException;
import com.vilvay.bloggingapp.service.AuthorService;
import com.vilvay.bloggingapp.service.PostsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class PostsControllerTest {

    private static final int POST_ID = 2;
    @Mock
    private PostsService postsService;

    @InjectMocks
    private PostsController postsController;


    @Test
    void testCreatePost() {
        PostsDTO postsDTO = new PostsDTO();
        when(postsService.savePosts(any(PostsDTO.class)))
                .thenReturn(new PostsDTO());

        ResponseEntity<PostsDTO> response = postsController.createPost(postsDTO);

        verify(postsService, times(1))
                .savePosts(any(PostsDTO.class));
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void testFindPosts() {
        when(postsService.findPostById(POST_ID))
                .thenReturn(new PostsDTO());

        ResponseEntity<PostsDTO> response = postsController.findPost(POST_ID);

        verify(postsService, times(1))
                .findPostById(POST_ID);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testUpdatePosts() throws ResourceNotFoundException {
        PostsDTO postsDTO = new PostsDTO();
        when(postsService.updatePostById(anyInt(), any(PostsDTO.class)))
                .thenReturn(new PostsDTO());

        ResponseEntity<PostsDTO> response = postsController.updatePost(POST_ID, postsDTO);

        verify(postsService, times(1))
                .updatePostById(POST_ID, postsDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testDeletePosts() throws ResourceNotFoundException {
        ResponseEntity<HttpStatus> response = postsController.deletePost(POST_ID);

        verify(postsService, times(1))
                .deletePostById(POST_ID);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}