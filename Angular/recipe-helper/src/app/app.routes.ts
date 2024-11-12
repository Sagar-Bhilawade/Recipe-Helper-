import { Routes } from '@angular/router';
import { HomePageComponent } from './home-page/home-page.component';
import { CreateEditRecipeComponent } from './create-edit-recipe/create-edit-recipe.component';

export const routes: Routes = [
  {path:'',component:HomePageComponent},
  {path:'create-edit-recipe',component:CreateEditRecipeComponent}

];
