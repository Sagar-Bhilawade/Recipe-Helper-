package com.recipe.helper.recipe_helper_webapp.service;

import com.recipe.helper.recipe_helper_webapp.dto.UpdateRecipeRequest;
import com.recipe.helper.recipe_helper_webapp.entity.Recipe;

import java.util.List;

public interface IRecipeService {
    public Recipe createRecipe(Recipe recipe);
    public Recipe getRecipeById(Long recipeId);
    public void deleteRecipe(Long recipeId);
    public void updateRecipe(Long recipeId, UpdateRecipeRequest recipe);
    public List<Recipe> getAllRecipes();
    public String likeUnlikeRecipe(Long recipeId, Long userId);

}
