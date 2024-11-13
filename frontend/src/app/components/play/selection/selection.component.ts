import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { Choice } from '../../../models/models';

@Component({
  selector: 'app-selection',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './selection.component.html',
  styleUrl: './selection.component.scss'
})
export class SelectionComponent {
  @Input({required: true}) playing: boolean = false;
  @Input({required: true}) choice?: Choice;
  @Input() reverse: boolean = false;
}
