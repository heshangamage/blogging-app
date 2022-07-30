package com.vilvay.bloggingapp.repository;

import com.vilvay.bloggingapp.entity.Comments;
import com.vilvay.bloggingapp.entity.Posts;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepository extends CrudRepository<Comments, Integer> {

    List<Comments> findByPostId(int id);
}
