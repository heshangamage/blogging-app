package com.vilvay.bloggingapp.security;

import com.vilvay.bloggingapp.entity.Author;
import com.vilvay.bloggingapp.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Author author = authorRepository.findByUserName(username);
        if (author == null) {
            throw new UsernameNotFoundException("Author Not Found");
        }
        return new CustomUserDetails(author);
    }
}
