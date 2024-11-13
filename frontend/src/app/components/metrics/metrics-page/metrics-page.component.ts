import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { catchError, finalize, Observable, of } from 'rxjs';
import { MetricsDTO, PlayDTO } from '../../../models/models';
import { MetricsService } from '../../../services/metrics.service';

@Component({
  selector: 'app-metrics-page',
  standalone: true,
  imports: [ CommonModule ],
  templateUrl: './metrics-page.component.html',
  styleUrl: './metrics-page.component.scss'
})
export class MetricsPageComponent {
  protected metrics?: Observable<MetricsDTO | undefined>;
  protected metricsErrorMessage?: string;
  protected playsErrorMessage?: string;
  protected loading: number = 0;
  protected plays?: Observable<PlayDTO[] | undefined>;

  constructor(private metricsService: MetricsService){
    this.refresh();
  }

  protected refresh(){
    this.loading = 2;
    this.metricsErrorMessage = this.playsErrorMessage = undefined;
    
    this.metrics = this.metricsService.getMetrics().pipe(
      catchError((err: any) => {
        this.metricsErrorMessage = err.message || err.toString();
        return of(undefined);
      }),
      finalize(() => this.loading--)
    );

    this.plays = this.metricsService.getPlays().pipe(
      catchError((err: any) => {
        this.playsErrorMessage = err.message || err.toString();
        return of(undefined);
      }),
      finalize(() => this.loading--)
    );
  }
}
