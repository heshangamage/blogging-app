package com.vilvay.bloggingapp.service;

import com.vilvay.bloggingapp.dtos.CommentsDTO;
import com.vilvay.bloggingapp.dtos.PostsDTO;
import com.vilvay.bloggingapp.exception.ResourceNotFoundException;

import java.util.List;

public interface CommentService {

    public CommentsDTO createComment(CommentsDTO request);

    public CommentsDTO findCommentById(int id);

    public List<CommentsDTO> getAllCommentsByPostId(int id);

    public CommentsDTO updateCommentById(int id, CommentsDTO request) throws ResourceNotFoundException;

    public void deleteCommentById(int id) throws ResourceNotFoundException;
}
