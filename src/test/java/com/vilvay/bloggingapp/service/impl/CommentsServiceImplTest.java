package com.vilvay.bloggingapp.service.impl;

import com.vilvay.bloggingapp.dtos.CommentsDTO;
import com.vilvay.bloggingapp.entity.Author;
import com.vilvay.bloggingapp.entity.Comments;
import com.vilvay.bloggingapp.repository.CommentsRepository;
import com.vilvay.bloggingapp.util.mapper.MapStructCommentsMapper;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommentsServiceImplTest {

    private static final int COMMENT_ID = 1;
    private static final String NAME = "some comment name";
    private static final String BODY = "some body";
    private static final  String EMAIL = "someone@gmail.com";
    private static final int POST_ID = 2;

    @Spy
    private MapStructCommentsMapper mapStructCommentsMapper = Mappers.getMapper(MapStructCommentsMapper.class);

    @Mock
    private CommentsRepository commentsRepository;

    @InjectMocks
    CommentsServiceImpl commentsServiceImpl;

    @Test
    void testSaveCommentSuccessTest() {
        CommentsDTO commentsDTO = composeCommentDTO();
        Comments comments = composeCommentEntity(commentsDTO);
        when(commentsRepository.save(any(Comments.class))).thenReturn(comments);

        CommentsDTO saveDto = commentsServiceImpl.createComment(commentsDTO);
        assertEquals(NAME, saveDto.getName());
    }

    @Test
    void testFindCommentsById() {
        when(commentsRepository.findById(COMMENT_ID)).thenReturn(Optional.of(new Comments(COMMENT_ID, POST_ID, NAME, EMAIL, BODY, Instant.now(), Instant.now())));
        CommentsDTO resultDto = commentsServiceImpl.findCommentById(COMMENT_ID);
        assertEquals(COMMENT_ID, resultDto.getId());
    }

    private List<Author> getAllCommentsList() {
        List<Author> authorList = new ArrayList<>();
        authorList.add(new Author(1, "some author 1", "some user name 1", "some email 1", "some address 1", "some password 1", "some role 1"));
        authorList.add(new Author(2, "some author 2", "some user name 2", "some email 2", "some address 2", "some password 2", "some role 2"));
        authorList.add(new Author(3, "some author 3", "some user name 3", "some email 3", "some address 3", "some password 3", "some role 3"));
        return authorList;
    }

    private CommentsDTO composeCommentDTO() {
        CommentsDTO commentsDTO = new CommentsDTO();
        commentsDTO.setId(COMMENT_ID);
        commentsDTO.setName(NAME);
        commentsDTO.setBody(BODY);
        commentsDTO.setPostId(POST_ID);
        commentsDTO.setEmail(EMAIL);
        commentsDTO.setCreatedOn(Instant.now());
        commentsDTO.setModifiedOn(Instant.now());
        return commentsDTO;
    }

    private Comments composeCommentEntity(CommentsDTO commentsDTO) {
        Comments comments = new Comments();
        comments.setId(commentsDTO.getId());
        comments.setName(commentsDTO.getName());
        comments.setBody(commentsDTO.getBody());
        comments.setPostId(commentsDTO.getPostId());
        comments.setCreatedOn(commentsDTO.getCreatedOn());
        comments.setModifiedOn(commentsDTO.getModifiedOn());
        return comments;
    }
}