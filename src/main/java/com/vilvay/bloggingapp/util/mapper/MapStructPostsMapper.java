package com.vilvay.bloggingapp.util.mapper;

import com.vilvay.bloggingapp.dtos.PostsDTO;
import com.vilvay.bloggingapp.entity.Posts;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MapStructPostsMapper {

    Posts postsDtoToEntity(PostsDTO postsDTO);

    PostsDTO entityToPostDto(Posts posts);

    @Mapping(target = "createdOn", ignore = true)
    Posts updatePostsDtoToEntity(PostsDTO request, @MappingTarget Posts posts);
}
