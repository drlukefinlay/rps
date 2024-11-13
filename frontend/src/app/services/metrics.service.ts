import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MetricsDTO, PlayDTO } from '../models/models';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MetricsService {

  constructor(private http: HttpClient) { }

  public getMetrics(): Observable<MetricsDTO> {
    return this.http.get<MetricsDTO>('http://localhost:8080/api/metrics');
  };

  //It would be easy to add pagination using the parameters here...
  public getPlays(): Observable<PlayDTO[]> {
    return this.http.get<PlayDTO[]>('http://localhost:8080/api/plays');
  };
}
