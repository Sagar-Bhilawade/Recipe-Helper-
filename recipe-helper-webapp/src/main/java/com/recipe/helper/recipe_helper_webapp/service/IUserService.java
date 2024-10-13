package com.recipe.helper.recipe_helper_webapp.service;

import com.recipe.helper.recipe_helper_webapp.entity.Recipe;
import com.recipe.helper.recipe_helper_webapp.entity.User;

import java.util.List;

public interface IUserService {
    public User createUser(User user);
    public String deleteUser(Long userId);
    public List<User> getAllUsers();

    public User getUserByEmailAndPassword(String email, String password);
    public List<Recipe> getUserLikedRecipes(Long userId);

}
