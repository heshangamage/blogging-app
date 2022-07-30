package com.vilvay.bloggingapp.repository;

import com.vilvay.bloggingapp.entity.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Integer> {

    Author findByUserName(String userName);
}
