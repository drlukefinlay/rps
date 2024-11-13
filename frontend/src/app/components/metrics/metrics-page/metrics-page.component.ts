import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { catchError, finalize, Observable, of } from 'rxjs';
import { MetricsDTO } from '../../../models/models';
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
  protected errorMessage?: string;
  protected loading: boolean = false;

  constructor(private metricsService: MetricsService){
    this.refresh();
  }

  protected refresh() {
    this.loading = true;
    this.errorMessage = undefined;
    this.metrics = this.metricsService.getMetrics().pipe(
      catchError((err: any) => {
        this.errorMessage = err.message || err.toString();
        return of(undefined); // reset message to placeholder
      }),
      finalize(() => this.loading = false)
    );
  }
  
}
