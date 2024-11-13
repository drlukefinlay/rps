import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MetricsDTO } from '../models/models';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MetricsService {

  constructor(private http: HttpClient) { }

  public getMetrics(): Observable<MetricsDTO> {
    return this.http.get<MetricsDTO>('http://localhost:8080/api/metrics');
  };

}
