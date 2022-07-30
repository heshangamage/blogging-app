package com.vilvay.bloggingapp.service.impl;

import com.vilvay.bloggingapp.dtos.PostsDTO;
import com.vilvay.bloggingapp.entity.Posts;
import com.vilvay.bloggingapp.exception.ResourceNotFoundException;
import com.vilvay.bloggingapp.repository.PostsRepository;
import com.vilvay.bloggingapp.service.PostsService;
import com.vilvay.bloggingapp.util.mapper.MapStructPostsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostsService {

    private final MapStructPostsMapper mapStructPostsMapper;
    private final PostsRepository postsRepository;

    @Autowired
    public PostServiceImpl(MapStructPostsMapper mapStructPostsMapper, PostsRepository postsRepository) {
        this.mapStructPostsMapper = mapStructPostsMapper;
        this.postsRepository = postsRepository;
    }


    @Override
    public PostsDTO createPosts(PostsDTO request) {
        request.setCreatedOn(Instant.now());
        request.setModifiedOn(Instant.now());
        Posts postsRequest = mapStructPostsMapper.postsDtoToEntity(request);
        Posts postsResponse = postsRepository.save(postsRequest);
        return mapStructPostsMapper.entityToPostDto(postsResponse);
    }

    @Override
    public List<PostsDTO> getAllPostsByAuthorId(int id) {
        Optional<List<Posts>> responseOptional = postsRepository.findByAuthorId(id);
        if (responseOptional.isPresent()) {
            List<Posts> response = postsRepository.findByAuthorId(id).get();
            List<PostsDTO> responseDTOs = new ArrayList<>();
            response.forEach(post -> responseDTOs.add(mapStructPostsMapper.entityToPostDto(post)));
            return responseDTOs;
        } else {
            return null;
        }
    }

    @Override
    public PostsDTO findPostById(int id) {
        Posts post = postsRepository.findById(id).get();
        PostsDTO postsDTO = mapStructPostsMapper.entityToPostDto(post);
        return postsDTO;
    }

    @Override
    public PostsDTO updatePostById(int id, PostsDTO request) throws ResourceNotFoundException {
        Optional<Posts> postsOptional = postsRepository.findById(id);

        if (postsOptional.isPresent()) {
            Posts posts = postsOptional.get();
            request.setId(posts.getId());
            request.setAuthorId(posts.getAuthorId());
            request.setModifiedOn(Instant.now());
            posts = mapStructPostsMapper.updatePostsDtoToEntity(request, posts);
            posts = postsRepository.save(posts);
            return mapStructPostsMapper.entityToPostDto(posts);
        } else {
            throw new ResourceNotFoundException("Cannot find the author");
        }
    }

    @Override
    public void deletePostById(int id) throws ResourceNotFoundException {
        Optional<Posts> postsOptional = postsRepository.findById(id);

        if (postsOptional.isPresent()) {
            postsRepository.delete(postsOptional.get());
        } else {
            throw new ResourceNotFoundException("Cannot find the author");
        }
    }
}
