import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { PlayerStrategyDTO, RoundOutcomeDTO } from '../models/models';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RpsService {

  constructor(private http: HttpClient) {}

  public play(strategy: PlayerStrategyDTO): Observable<RoundOutcomeDTO> {
    const url = '/api/play';
    return this.http.post<RoundOutcomeDTO>(url, strategy);
  }
}
