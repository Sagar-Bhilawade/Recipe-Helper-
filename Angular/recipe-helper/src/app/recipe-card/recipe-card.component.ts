import { Component, Input } from '@angular/core';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';

@Component({
  selector: 'app-recipe-card',
  standalone: true,
  imports: [MatButtonModule, MatCardModule],
  templateUrl: './recipe-card.component.html',
  styleUrl: './recipe-card.component.scss'
})
export class RecipeCardComponent {
  // @Input() title: string;
  // @Input() category: string;
  // @Input() imageUrl: string;
  // @Input() description: string;

}
