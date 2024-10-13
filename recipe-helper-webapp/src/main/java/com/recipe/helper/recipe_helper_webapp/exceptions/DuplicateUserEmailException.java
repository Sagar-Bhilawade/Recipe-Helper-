package com.recipe.helper.recipe_helper_webapp.exceptions;

public class DuplicateUserEmailException extends RuntimeException{
    public DuplicateUserEmailException(String message) {
        super(message);
    }
}
