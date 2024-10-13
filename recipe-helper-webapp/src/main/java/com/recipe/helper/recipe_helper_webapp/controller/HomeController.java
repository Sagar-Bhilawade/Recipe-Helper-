package com.recipe.helper.recipe_helper_webapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    public HomeController() {
    }
    @GetMapping("/home")
    public String homeController(){
        return "Welcome on the home controller";
    }
}
