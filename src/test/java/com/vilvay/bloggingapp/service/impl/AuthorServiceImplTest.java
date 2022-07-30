package com.vilvay.bloggingapp.service.impl;

import com.vilvay.bloggingapp.dtos.AuthorDTO;
import com.vilvay.bloggingapp.entity.Author;
import com.vilvay.bloggingapp.exception.ResourceNotFoundException;
import com.vilvay.bloggingapp.repository.AuthorRepository;
import com.vilvay.bloggingapp.util.mapper.MapStructAuthorMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {

    private static final int AUTHOR_ID = 1;
    private static final String AUTHOR_NAME = "some author";
    private static final String ADDRESS = "some address";
    private static final String PASSWORD = "123";
    private static final String EMAIL = "some@gmail.com";
    private static final String ROLE = "ADMIN";
    private static final String USER_NAME = "someusername";

    @Spy
    private MapStructAuthorMapper mapStructAuthorMapper = Mappers.getMapper(MapStructAuthorMapper.class);

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    AuthorServiceImpl authorServiceImpl;

    @Test
    void testSaveAuthorSuccessTest() {
        AuthorDTO authorDTO = composeAuthorDTO();
        Author author = composeAuthorEntity(authorDTO);
        when(authorRepository.save(any(Author.class))).thenReturn(author);

        AuthorDTO saveDto = authorServiceImpl.saveAuthor(authorDTO);
        assertEquals(USER_NAME, saveDto.getUserName());
    }

    @Test
    void testGetAllAuthors() {
        when(authorRepository.findAll()).thenReturn(getAllAuthorsList());
        List<AuthorDTO> authorDTOs = authorServiceImpl.getAllAuthors();
        assertEquals(AUTHOR_ID, authorDTOs.get(0).getId());
    }

    @Test
    void testFindAuthorById() {
        when(authorRepository.findById(AUTHOR_ID)).thenReturn(Optional.of(new Author(1, "some author 1", "some user name 1", "some email 1", "some address 1", "some password 1", "some role 1")));
        AuthorDTO resultDto = authorServiceImpl.findAuthorById(AUTHOR_ID);
        assertEquals(AUTHOR_ID, resultDto.getId());
    }

    @Test
    void testUpdateAuthorById() throws ResourceNotFoundException {
        AuthorDTO authorDTO = composeAuthorDTO();
        Author author = composeAuthorEntity(authorDTO);
        when(authorRepository.findById(AUTHOR_ID)).thenReturn(Optional.of(new Author(1, "some author 1", "some user name 1", "some email 1", "some address 1", "some password 1", "some role 1")));
        when(authorRepository.save(any(Author.class))).thenReturn(author);
        AuthorDTO resultDto = authorServiceImpl.updateAuthorById(AUTHOR_ID, authorDTO);
        assertEquals(AUTHOR_NAME, resultDto.getName());
    }

    private List<Author> getAllAuthorsList() {
        List<Author> authorList = new ArrayList<>();
        authorList.add(new Author(1, "some author 1", "some user name 1", "some email 1", "some address 1", "some password 1", "some role 1"));
        authorList.add(new Author(2, "some author 2", "some user name 2", "some email 2", "some address 2", "some password 2", "some role 2"));
        authorList.add(new Author(3, "some author 3", "some user name 3", "some email 3", "some address 3", "some password 3", "some role 3"));
        return authorList;
    }

    private AuthorDTO composeAuthorDTO() {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(AUTHOR_ID);
        authorDTO.setName(AUTHOR_NAME);
        authorDTO.setAddress(ADDRESS);
        authorDTO.setPassword(PASSWORD);
        authorDTO.setEmail(EMAIL);
        authorDTO.setRole(ROLE);
        authorDTO.setUserName(USER_NAME);
        return authorDTO;
    }

    private Author composeAuthorEntity(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setId(authorDTO.getId());
        author.setName(authorDTO.getName());
        author.setAddress(authorDTO.getAddress());
        author.setEmail(authorDTO.getEmail());
        author.setRole(authorDTO.getRole());
        author.setUserName(authorDTO.getUserName());
        author.setPassword(authorDTO.getPassword());
        return author;
    }

}