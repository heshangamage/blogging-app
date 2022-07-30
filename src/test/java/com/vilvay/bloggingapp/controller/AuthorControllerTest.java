package com.vilvay.bloggingapp.controller;

import com.vilvay.bloggingapp.dtos.AuthorDTO;
import com.vilvay.bloggingapp.exception.ResourceNotFoundException;
import com.vilvay.bloggingapp.service.AuthorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorControllerTest {

    private static final int AUTHOR_ID = 1;
    @Mock
    private AuthorService authorService;

    @InjectMocks
    private AuthorController authorController;


    @Test
    void testCreateAuthor() {
        AuthorDTO authorDTO = new AuthorDTO();
        when(authorService.saveAuthor(any(AuthorDTO.class)))
                .thenReturn(new AuthorDTO());

        ResponseEntity<AuthorDTO> response = authorController.createAuthor(authorDTO);

        verify(authorService, times(1))
                .saveAuthor(any(AuthorDTO.class));
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void testGetAllAuthors() {
        when(authorService.getAllAuthors())
                .thenReturn(new ArrayList<AuthorDTO>());

        ResponseEntity<List<AuthorDTO>> response = authorController.getAllAuthors();

        verify(authorService, times(1))
                .getAllAuthors();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testFindAuthor() {
        when(authorService.findAuthorById(AUTHOR_ID))
                .thenReturn(new AuthorDTO());

        ResponseEntity<AuthorDTO> response = authorController.findAuthor(AUTHOR_ID);

        verify(authorService, times(1))
                .findAuthorById(AUTHOR_ID);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testUpdateAuthor() throws ResourceNotFoundException {
        AuthorDTO authorDTO = new AuthorDTO();
        when(authorService.updateAuthorById(anyInt(), any(AuthorDTO.class)))
                .thenReturn(new AuthorDTO());

        ResponseEntity<AuthorDTO> response = authorController.updateAuthor(AUTHOR_ID, authorDTO);

        verify(authorService, times(1))
                .updateAuthorById(AUTHOR_ID, authorDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testDeleteAuthor() throws ResourceNotFoundException {
        ResponseEntity<HttpStatus> response = authorController.deleteAuthor(AUTHOR_ID);

        verify(authorService, times(1))
                .deleteAuthor(AUTHOR_ID);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}