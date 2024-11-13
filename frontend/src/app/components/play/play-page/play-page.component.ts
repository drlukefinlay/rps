import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Choice, Outcome } from '../../../models/models';
import { RpsService } from '../../../services/rps.service';
import { SelectionComponent } from "../selection/selection.component";

@Component({
  selector: 'app-play-page',
  standalone: true,
  imports: [SelectionComponent, CommonModule],
  templateUrl: './play-page.component.html',
  styleUrl: './play-page.component.scss'
})
export class PlayPageComponent {
  protected playerChoice?: Choice;
  protected computerChoice?: Choice;
  protected outcome?: Outcome;
  protected errorMessage?: string

  protected loading = false;
  protected playing = false;

  constructor(private rpsService: RpsService) { }

  protected playerSelected(playerChoice: Choice) {
    this.playing = false;
    this.loading = true;
    this.outcome = undefined;
    this.errorMessage = undefined;

    this.rpsService.play({ playerChoice }).subscribe({next: round => {
      this.loading = false;
      this.playing = true;
      setTimeout(() => {
        
        this.playerChoice = round.playerChoice;
        this.computerChoice = round.computerChoice;
        this.outcome = round.outcome;
        this.playing = false;
      }, 1000);
    }, error: (err: any)=>{
      this.loading = false;
      this.errorMessage = err.message || err.toString();
    }});
  }

}
