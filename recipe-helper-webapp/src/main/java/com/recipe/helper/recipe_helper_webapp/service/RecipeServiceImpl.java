package com.recipe.helper.recipe_helper_webapp.service;

import com.recipe.helper.recipe_helper_webapp.dto.UpdateRecipeRequest;
import com.recipe.helper.recipe_helper_webapp.entity.Recipe;
import com.recipe.helper.recipe_helper_webapp.exceptions.RecipeNotFoundException;
import com.recipe.helper.recipe_helper_webapp.exceptions.UserNotFoundException;
import com.recipe.helper.recipe_helper_webapp.repository.RecipeRepository;
import com.recipe.helper.recipe_helper_webapp.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class RecipeServiceImpl implements IRecipeService{
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Recipe createRecipe(Recipe recipe) {
        userRepository.findById(recipe.getUser().getId()).orElseThrow(()-> new UserNotFoundException("User does not exist with id :: "+recipe.getUser().getId()));
       return recipeRepository.save(recipe);
    }

    @Override
    public Recipe getRecipeById(Long recipeId) {
        return recipeRepository.findById(recipeId).orElseThrow(()->new RecipeNotFoundException("Recipe not found with id : "+recipeId));
    }

    @Override
    public void deleteRecipe(Long recipeId) {
        recipeRepository.findById(recipeId).orElseThrow(()->new RecipeNotFoundException("Cannot delete recipe cause of not found with id : "+recipeId));
        recipeRepository.deleteById(recipeId);

    }

    @Override
    public void updateRecipe(Long recipeId, UpdateRecipeRequest recipe) {
        Recipe updatedRecipe = recipeRepository.findById(recipeId).orElseThrow(()->new RecipeNotFoundException("Cannot Update recipe cause of not found with id : "+recipeId));
        updatedRecipe.setDescription(recipe.getDescription());
        updatedRecipe.setImage(recipe.getImage());
        updatedRecipe.setTitle(recipe.getTitle());
        updatedRecipe.setStepsToMake(recipe.getStepsToMake());
        updatedRecipe.setVegeterian(recipe.getVegeterian());
        recipeRepository.save(updatedRecipe);
    }

    @Override
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @Override
    public String likeUnlikeRecipe(Long recipeId, Long userId) {
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(()->new RecipeNotFoundException("Cannot Like recipe cause of not found with id : "+recipeId));
        userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("Cannot Like recipe cause of User does not exist with id :: "+userId));
        String message = null;
        if(recipe.getLikes().contains(userId)) {
            recipe.getLikes().remove(userId);
            message = "Removed recipe from user likes";
        }
        else {
            recipe.getLikes().add(userId);
            message = "Added recipe in User Likes";
        }

        return message;
    }
}
