package com.recipe.helper.recipe_helper_webapp.controller;

import com.recipe.helper.recipe_helper_webapp.dto.UpdateRecipeRequest;
import com.recipe.helper.recipe_helper_webapp.entity.Recipe;
import com.recipe.helper.recipe_helper_webapp.service.IRecipeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/recipe")
public class RecipeController {
        @Autowired
        private IRecipeService recipeService;

        // Create a new recipe
        @PostMapping("/create-recipe")
        public ResponseEntity<Recipe> createRecipe(@Valid @RequestBody Recipe recipe) {
            return new ResponseEntity<>(recipeService.createRecipe(recipe), HttpStatus.CREATED);
        }

        // Get a recipe by ID
        @GetMapping("/recipe/{recipeId}")
        public ResponseEntity<Recipe> getRecipeById(@PathVariable Long recipeId) {
            Recipe recipe = recipeService.getRecipeById(recipeId);
            return new ResponseEntity<>(recipe, HttpStatus.OK);
        }

        // Get all recipes
        @GetMapping("/all-recipes")
        public ResponseEntity<List<Recipe>> getAllRecipes() {
            List<Recipe> recipes = recipeService.getAllRecipes();
            return new ResponseEntity<>(recipes, HttpStatus.OK);
        }

        // Update a recipe by ID
        @PutMapping("/update-recipe/{recipeId}")
        public ResponseEntity<Recipe> updateRecipe(@PathVariable Long recipeId,@Valid @RequestBody UpdateRecipeRequest recipe) {
            recipeService.updateRecipe(recipeId, recipe);
            return new ResponseEntity<>(recipeService.getRecipeById(recipeId), HttpStatus.OK);
        }

        // Delete a recipe by ID
        @DeleteMapping("/delete-recipe/{recipeId}")
        public ResponseEntity<String> deleteRecipe(@PathVariable Long recipeId) {
            recipeService.deleteRecipe(recipeId);
            return new ResponseEntity<>("Recipe deleted successfully!", HttpStatus.OK);
        }

        // Like or Unlike a recipe
        @PostMapping("/{recipeId}/like-unlike")
        public ResponseEntity<String> likeUnlikeRecipe(@PathVariable Long recipeId, @RequestParam Long userId) {
            return new ResponseEntity<>(recipeService.likeUnlikeRecipe(recipeId, userId), HttpStatus.OK);
        }
    }


