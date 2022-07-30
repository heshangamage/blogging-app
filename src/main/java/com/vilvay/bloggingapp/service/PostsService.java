package com.vilvay.bloggingapp.service;

import com.vilvay.bloggingapp.dtos.PostsDTO;
import com.vilvay.bloggingapp.exception.ResourceNotFoundException;

import java.util.List;

public interface PostsService {

    public PostsDTO savePosts(PostsDTO request);

    public List<PostsDTO> getAllPostsByAuthorId(int id);

    public PostsDTO findPostById(int id);

    public PostsDTO updatePostById(int id, PostsDTO request) throws ResourceNotFoundException;

    public void deletePostById(int id) throws ResourceNotFoundException;
}
