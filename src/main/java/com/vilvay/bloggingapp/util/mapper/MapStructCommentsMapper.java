package com.vilvay.bloggingapp.util.mapper;

import com.vilvay.bloggingapp.dtos.CommentsDTO;
import com.vilvay.bloggingapp.entity.Comments;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MapStructCommentsMapper {

    Comments CommentsDtoToEntity(CommentsDTO commentDTO);

    CommentsDTO entityToCommentsDto(Comments comments);

    @Mapping(target = "createdOn", ignore = true)
    Comments updateCommentsDtoToEntity(CommentsDTO request, @MappingTarget Comments comments);

}
