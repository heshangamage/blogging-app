package com.vilvay.bloggingapp.service.impl;

import com.vilvay.bloggingapp.dtos.CommentsDTO;
import com.vilvay.bloggingapp.entity.Comments;
import com.vilvay.bloggingapp.exception.ResourceNotFoundException;
import com.vilvay.bloggingapp.repository.CommentsRepository;
import com.vilvay.bloggingapp.service.CommentService;
import com.vilvay.bloggingapp.util.mapper.MapStructCommentsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentsServiceImpl implements CommentService {

    private final MapStructCommentsMapper mapStructCommentsMapper;
    private final CommentsRepository commentsRepository;

    @Autowired
    public CommentsServiceImpl(MapStructCommentsMapper mapStructCommentsMapper,
                               CommentsRepository commentsRepository) {
        this.mapStructCommentsMapper = mapStructCommentsMapper;
        this.commentsRepository = commentsRepository;
    }

    @Override
    public CommentsDTO createComment(CommentsDTO request) {
        request.setCreatedOn(Instant.now());
        request.setModifiedOn(Instant.now());
        Comments requestComment = mapStructCommentsMapper.CommentsDtoToEntity(request);
        Comments responseComment = commentsRepository.save(requestComment);
        return mapStructCommentsMapper.entityToCommentsDto(responseComment);
    }

    @Override
    public CommentsDTO findCommentById(int id) {
        Comments comments = commentsRepository.findById(id).get();
        CommentsDTO commentsDTO = mapStructCommentsMapper.entityToCommentsDto(comments);
        return commentsDTO;
    }

    @Override
    public List<CommentsDTO> getAllCommentsByPostId(int id) {
        List<Comments> response = commentsRepository.findByPostId(id);
        if (!response.isEmpty()) {
            List<CommentsDTO> responseDTOs = new ArrayList<>();
            response.forEach(comment -> responseDTOs.add(mapStructCommentsMapper.entityToCommentsDto(comment)));
            return responseDTOs;
        } else {
            return null;
        }
    }

    @Override
    public CommentsDTO updateCommentById(int id, CommentsDTO request) throws ResourceNotFoundException {
        Optional<Comments> commentsOptional = commentsRepository.findById(id);

        if (commentsOptional.isPresent()) {
            Comments comments = commentsOptional.get();
            request.setModifiedOn(Instant.now());
            request.setEmail(comments.getEmail());
            request.setPostId(comments.getPostId());
            request.setId(comments.getId());
            comments = mapStructCommentsMapper.updateCommentsDtoToEntity(request, comments);
            comments = commentsRepository.save(comments);
            return mapStructCommentsMapper.entityToCommentsDto(comments);
        } else {
            throw new ResourceNotFoundException("Cannot find the author");
        }
    }

    @Override
    public void deleteCommentById(int id) throws ResourceNotFoundException {
        Optional<Comments> commentsOptional = commentsRepository.findById(id);

        if (commentsOptional.isPresent()) {
            commentsRepository.delete(commentsOptional.get());
        } else {
            throw new ResourceNotFoundException("Cannot find the author");
        }
    }
}
