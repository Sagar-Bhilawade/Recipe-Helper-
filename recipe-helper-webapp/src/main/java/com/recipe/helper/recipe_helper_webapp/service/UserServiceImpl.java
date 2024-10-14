package com.recipe.helper.recipe_helper_webapp.service;

import com.recipe.helper.recipe_helper_webapp.entity.Recipe;
import com.recipe.helper.recipe_helper_webapp.entity.User;
import com.recipe.helper.recipe_helper_webapp.exceptions.DuplicateUserEmailException;
import com.recipe.helper.recipe_helper_webapp.exceptions.UserNotFoundException;
import com.recipe.helper.recipe_helper_webapp.repository.RecipeRepository;
import com.recipe.helper.recipe_helper_webapp.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RecipeRepository recipeRepository;


    private final PasswordEncoder encoder;

    public UserServiceImpl(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public User createUser(User user) {
        User newUser= null;
        if(userRepository.findByEmail(user.getEmail()).isEmpty())
        {    user.setPassword(encoder.encode(user.getPassword()));
             newUser = userRepository.save(user);

        }
        else {
           String message = "Duplicate entry for email id :"+user.getEmail() +"\n Try with another email id";
            throw new DuplicateUserEmailException(message);
        }

        return newUser;
    }

    @Override
    public String deleteUser(Long userId) {
        userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("User does not exist with id :: "+userId));
        userRepository.deleteById(userId);
        return "User deleted successfully with id "+userId;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByEmailAndPassword(String email, String password) {
        userRepository.findByEmail(email).orElseThrow(()-> new UserNotFoundException("User does not exist with id :: "+email));
       return userRepository.findByEmailAndPassword(email, password).orElseThrow(()->new UserNotFoundException("Password is incorrect for emailId : "+email));

    }

    @Override
    public List<Recipe> getUserLikedRecipes(Long userId) {
        userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("User does not exist with id :: "+userId));
        return recipeRepository.findRecipesLikedByUser(userId);
    }


}
