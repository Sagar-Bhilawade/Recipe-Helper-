package com.recipe.helper.recipe_helper_webapp.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdateRecipeRequest {
    @NotBlank(message = "Title cannot be empty")
    @Size(min = 1, max = 100, message = "Title must be between 1 and 100 characters")
    private String title;

    private String description;

    private String image;

    @NotBlank(message = "Steps to make cannot be empty")
    private String stepsToMake;

    @NotNull(message = "Vegetarian status cannot be null")
    private Boolean vegeterian;

}
