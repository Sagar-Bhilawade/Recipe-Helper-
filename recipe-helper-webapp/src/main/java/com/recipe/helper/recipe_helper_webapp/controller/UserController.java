package com.recipe.helper.recipe_helper_webapp.controller;

import com.recipe.helper.recipe_helper_webapp.dto.SignInRequest;
import com.recipe.helper.recipe_helper_webapp.entity.Recipe;
import com.recipe.helper.recipe_helper_webapp.entity.User;
import com.recipe.helper.recipe_helper_webapp.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;


    @PostMapping("/signup")
    public ResponseEntity<User> CreateUser(@Valid @RequestBody User user){
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/signin")
    public ResponseEntity<User> getUserByEmailAndPassword(@Valid @RequestBody SignInRequest signInRequest){
        return new ResponseEntity<>(userService.getUserByEmailAndPassword(signInRequest.getEmail(), signInRequest.getPassword()), HttpStatus.OK);
    }

    @DeleteMapping("/delete-user/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/get-all-users")
    public ResponseEntity<List<User>> getAllUser() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/liked-recipes")
    public ResponseEntity<List<Recipe>> getUserLikedRecipes(@PathVariable Long userId)
    {
        return new ResponseEntity<>(userService.getUserLikedRecipes(userId), HttpStatus.OK);
    }


}
