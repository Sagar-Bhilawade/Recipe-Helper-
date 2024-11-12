import { Component, OnInit } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { MatFormFieldModule } from '@angular/material/form-field';

@Component({
  selector: 'app-create-edit-recipe',
  standalone: true,
  imports: [FormsModule,MatFormFieldModule],
templateUrl: './create-edit-recipe.component.html',
  styleUrl: './create-edit-recipe.component.scss'
})
export class CreateEditRecipeComponent implements OnInit {
  isEditMode: boolean = false;
  recipeId: string | null = null;
  steps: string[] = [];
  step: string = '';

  // Define a model for the form
  recipe = {
    title: '',
    description: '',
    image: '',
    stepsToMake: '',
    vegetarian: false
  };

  constructor(private route: ActivatedRoute) {}

  ngOnInit(): void {
    // Check if we're in edit mode by checking the route parameter
    this.route.paramMap.subscribe(params => {
      this.recipeId = params.get('id');
      if (this.recipeId) {
        this.isEditMode = true;
        this.loadRecipeForEdit(this.recipeId);
      }
    });
  }

  addStep() {
    if (this.step) {
      this.steps.push(this.step);
      this.step = ''; // Reset input after adding the step
    }
  }
  // Simulating fetching data for edit mode
  loadRecipeForEdit(id: string): void {
    // In a real app, you'd fetch the recipe data from an API using the ID
    const mockRecipe = {
      title: 'Sample Recipe',
      description: 'This is a sample description',
      image: 'sample-image-url',
      stepsToMake: 'Step 1, Step 2',
      vegetarian: true
    };

    // Assign the fetched data to the form model
    this.recipe = mockRecipe;
  }

  // Handle form submission for both create and update
  onSubmit(form: NgForm): void {
    if (this.isEditMode) {
      this.updateRecipe(form);
    } else {
      this.createRecipe(form);
    }
  }

  createRecipe(form: NgForm): void {
    console.log('Creating recipe:', form.value);
    // Implement the API call to create the recipe
  }

  updateRecipe(form: NgForm): void {
    console.log('Updating recipe:', form.value);
    // Implement the API call to update the recipe
  }
   onFileSelected(event: any) {
    const file: File = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = (e: any) => {
        this.recipe.image = e.target.result; // Save the base64 string to the recipe object
      };
      reader.readAsDataURL(file); // Convert the file to base64
    }
  }
}
