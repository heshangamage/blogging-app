package com.vilvay.bloggingapp.service.impl;

import com.vilvay.bloggingapp.repository.AuthorRepository;
import com.vilvay.bloggingapp.dtos.AuthorDTO;
import com.vilvay.bloggingapp.entity.Author;
import com.vilvay.bloggingapp.exception.ResourceNotFoundException;
import com.vilvay.bloggingapp.service.AuthorService;
import com.vilvay.bloggingapp.util.mapper.MapStructAuthorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final MapStructAuthorMapper mapStructAuthorMapper;
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(MapStructAuthorMapper mapStructAuthorMapper,
                             AuthorRepository authorRepository) {
        this.mapStructAuthorMapper = mapStructAuthorMapper;
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorDTO saveAuthor(AuthorDTO request) {
        request.setPassword(generateBCryptPassword(request.getPassword()));
        Author authorRequest = mapStructAuthorMapper.authorDtoToEntity(request);
        Author authorResponse = authorRepository.save(authorRequest);
        return mapStructAuthorMapper.entityToAuthorDto(authorResponse);
    }

    private String generateBCryptPassword(String originalPassword) {
        return BCrypt.hashpw(originalPassword, BCrypt.gensalt(12));
    }

    @Override
    public List<AuthorDTO> getAllAuthors() {
        List<AuthorDTO> authorDTOS = new ArrayList<>();
        authorRepository.findAll().forEach(author -> authorDTOS.add(mapStructAuthorMapper.entityToAuthorDto(author)));
        return authorDTOS;
    }

    @Override
    public AuthorDTO findAuthorById(int id) {
        Author author = authorRepository.findById(id).get();
        AuthorDTO authorDTO = mapStructAuthorMapper.entityToAuthorDto(author);
        return authorDTO;
    }

    @Override
    public AuthorDTO updateAuthorById(int id, AuthorDTO request)
            throws ResourceNotFoundException {
        Optional<Author> authorOptional = authorRepository.findById(id);

        if (authorOptional.isPresent()) {
            Author author = authorOptional.get();
            request.setId(author.getId());
            request.setPassword(generateBCryptPassword(request.getPassword()));
            author = mapStructAuthorMapper.updateAuthorDtoToEntity(request, author);
            author = authorRepository.save(author);
            return mapStructAuthorMapper.entityToAuthorDto(author);
        } else {
            throw new ResourceNotFoundException("Cannot find the author");
        }
    }

    @Override
    public void deleteAuthor(int id) throws ResourceNotFoundException {
        Optional<Author> authorOptional = authorRepository.findById(id);

        if (authorOptional.isPresent()) {
            authorRepository.delete(authorOptional.get());
        } else {
            throw new ResourceNotFoundException("Cannot find the author");
        }
    }


}
