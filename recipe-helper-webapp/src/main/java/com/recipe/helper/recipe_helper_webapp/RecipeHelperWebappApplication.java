package com.recipe.helper.recipe_helper_webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RecipeHelperWebappApplication {

	public static void main(String[] args) {
		System.out.println("####### Welcome to recipe helper webapp #######");
		SpringApplication.run(RecipeHelperWebappApplication.class, args);
	}

}
