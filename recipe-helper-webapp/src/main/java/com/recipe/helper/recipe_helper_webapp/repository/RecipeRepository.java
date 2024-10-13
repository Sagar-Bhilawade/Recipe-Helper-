package com.recipe.helper.recipe_helper_webapp.repository;

import com.recipe.helper.recipe_helper_webapp.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    @Query("SELECT r FROM Recipe r WHERE :userId IN elements(r.likes)")
    List<Recipe> findRecipesLikedByUser(@Param("userId") Long userId);
}
