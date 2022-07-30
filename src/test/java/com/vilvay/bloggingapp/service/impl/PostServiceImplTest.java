package com.vilvay.bloggingapp.service.impl;

import com.vilvay.bloggingapp.dtos.AuthorDTO;
import com.vilvay.bloggingapp.dtos.PostsDTO;
import com.vilvay.bloggingapp.entity.Author;
import com.vilvay.bloggingapp.entity.Posts;
import com.vilvay.bloggingapp.exception.ResourceNotFoundException;
import com.vilvay.bloggingapp.repository.AuthorRepository;
import com.vilvay.bloggingapp.repository.PostsRepository;
import com.vilvay.bloggingapp.util.mapper.MapStructAuthorMapper;
import com.vilvay.bloggingapp.util.mapper.MapStructPostsMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {

    private static final int POST_ID = 1;
    private static final String TITLE = "some title";
    private static final String BODY = "some body";
    private static final int AUTHOR_ID = 2;

    @Spy
    private MapStructPostsMapper mapStructPostsMapper = Mappers.getMapper(MapStructPostsMapper.class);

    @Mock
    private PostsRepository postsRepository;

    @InjectMocks
    PostServiceImpl postServiceImpl;

    @Test
    void testSaveAuthorSuccessTest() {
        PostsDTO postsDTO = composeAuthorDTO();
        Posts posts = composePostEntity(postsDTO);
        when(postsRepository.save(any(Posts.class))).thenReturn(posts);

        PostsDTO saveDto = postServiceImpl.savePosts(postsDTO);
        assertEquals(TITLE, saveDto.getTitle());
    }

    @Test
    void testFindPostsById() {
        when(postsRepository.findById(POST_ID)).thenReturn(Optional.of(new Posts(POST_ID, "some post title", "some post body", AUTHOR_ID, Instant.now(), Instant.now())));
        PostsDTO resultDto = postServiceImpl.findPostById(POST_ID);
        assertEquals(POST_ID, resultDto.getId());
    }

    private List<Author> getAllAuthorsList() {
        List<Author> authorList = new ArrayList<>();
        authorList.add(new Author(1, "some author 1", "some user name 1", "some email 1", "some address 1", "some password 1", "some role 1"));
        authorList.add(new Author(2, "some author 2", "some user name 2", "some email 2", "some address 2", "some password 2", "some role 2"));
        authorList.add(new Author(3, "some author 3", "some user name 3", "some email 3", "some address 3", "some password 3", "some role 3"));
        return authorList;
    }

    private PostsDTO composeAuthorDTO() {
        PostsDTO postsDTO = new PostsDTO();
        postsDTO.setId(AUTHOR_ID);
        postsDTO.setTitle(TITLE);
        postsDTO.setBody(BODY);
        postsDTO.setAuthorId(AUTHOR_ID);
        postsDTO.setCreatedOn(Instant.now());
        postsDTO.setModifiedOn(Instant.now());
        return postsDTO;
    }

    private Posts composePostEntity(PostsDTO postsDTO) {
        Posts posts = new Posts();
        posts.setId(postsDTO.getId());
        posts.setTitle(postsDTO.getTitle());
        posts.setBody(postsDTO.getBody());
        posts.setAuthorId(postsDTO.getAuthorId());
        posts.setCreatedOn(postsDTO.getCreatedOn());
        posts.setModifiedOn(postsDTO.getModifiedOn());
        return posts;
    }


}