package com.recipe.helper.recipe_helper_webapp.exceptions;

public class RecipeNotFoundException extends RuntimeException{
    public RecipeNotFoundException(String message) {

        super(message);
    }
}
