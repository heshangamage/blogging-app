package com.vilvay.bloggingapp.repository;

import com.vilvay.bloggingapp.entity.Posts;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostsRepository extends CrudRepository<Posts, Integer> {

    Optional<List<Posts>> findByAuthorId(int authorId);

}
