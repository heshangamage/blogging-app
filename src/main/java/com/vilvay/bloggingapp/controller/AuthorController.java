package com.vilvay.bloggingapp.controller;

import com.vilvay.bloggingapp.dtos.AuthorDTO;
import com.vilvay.bloggingapp.dtos.PostsDTO;
import com.vilvay.bloggingapp.exception.ResourceNotFoundException;
import com.vilvay.bloggingapp.service.AuthorService;
import com.vilvay.bloggingapp.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/authors")
public class AuthorController {

    private final AuthorService authorService;

    private final PostsService postsService;

    @Autowired
    public AuthorController(AuthorService authorService, PostsService postsService) {
        this.authorService = authorService;
        this.postsService = postsService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
        List<AuthorDTO> authorsList = authorService.getAllAuthors();
        return new ResponseEntity<>(authorsList, HttpStatus.OK);
    }

    @GetMapping("/{authorId}")
    public ResponseEntity<AuthorDTO> findAuthor(@PathVariable("authorId") int authorId) {
        return new ResponseEntity<>(authorService.findAuthorById(authorId), HttpStatus.OK);
    }

    @GetMapping("/{authorId}/posts")
    public ResponseEntity<List<PostsDTO>> findAllPostsByAuthorId(@PathVariable("authorId") int authorId) {
        return new ResponseEntity<>(postsService.getAllPostsByAuthorId(authorId), HttpStatus.OK);
    }

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorDTO authorDTORequest) {
        return new ResponseEntity<>(authorService.saveAuthor(authorDTORequest), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{authorId}", produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable("authorId") int authorId,
                                                      @RequestBody AuthorDTO request) throws ResourceNotFoundException {
        return new ResponseEntity<>(authorService.updateAuthorById(authorId, request), HttpStatus.OK);
    }

    @DeleteMapping("/{authorId}")
    public ResponseEntity<HttpStatus> deleteAuthor(@PathVariable("authorId") int authorId)
            throws ResourceNotFoundException {
        authorService.deleteAuthor(authorId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

