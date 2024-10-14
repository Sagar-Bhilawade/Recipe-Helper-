package com.recipe.helper.recipe_helper_webapp.security;

import com.recipe.helper.recipe_helper_webapp.entity.User;
import com.recipe.helper.recipe_helper_webapp.exceptions.UserNotFoundException;
import com.recipe.helper.recipe_helper_webapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(()->new UserNotFoundException("User does not exist with email :: "+username));
        return new CustomUserDetails(user);
    }
}
