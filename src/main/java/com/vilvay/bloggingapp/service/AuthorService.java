package com.vilvay.bloggingapp.service;

import com.vilvay.bloggingapp.dtos.AuthorDTO;
import com.vilvay.bloggingapp.exception.ResourceNotFoundException;

import java.util.List;

public interface AuthorService {

    public AuthorDTO saveAuthor(AuthorDTO request);

    public List<AuthorDTO> getAllAuthors();

    public AuthorDTO findAuthorById(int id);

    public AuthorDTO updateAuthorById(int id, AuthorDTO request) throws ResourceNotFoundException;

    public void deleteAuthor(int id) throws ResourceNotFoundException;
}
