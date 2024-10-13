package com.recipe.helper.recipe_helper_webapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotBlank(message = "Title cannot be empty")
    @Size(min = 1, max = 100, message = "Title must be between 1 and 100 characters")
    private String title;


    private String description;

    private String image;

    private String stepsToMake;

    private boolean vegeterian;

    @NotNull(message = "Created at cannot be null")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull(message = "User cannot be null")
    private User user;

    @ElementCollection
    @CollectionTable(name = "recipe_likes", joinColumns = @JoinColumn(name = "recipe_id"))
    @Column(name = "user_id")
    private List<Long> likes;

}
