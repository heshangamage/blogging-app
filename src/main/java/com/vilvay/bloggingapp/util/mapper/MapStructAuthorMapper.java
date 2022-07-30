package com.vilvay.bloggingapp.util.mapper;

import com.vilvay.bloggingapp.dtos.AuthorDTO;
import com.vilvay.bloggingapp.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MapStructAuthorMapper {

    Author authorDtoToEntity(AuthorDTO authorDTO);

    AuthorDTO entityToAuthorDto(Author author);

    Author updateAuthorDtoToEntity(AuthorDTO request, @MappingTarget Author author);
}
