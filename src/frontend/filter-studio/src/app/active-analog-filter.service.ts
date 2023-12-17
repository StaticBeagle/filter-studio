import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ActiveAnalogFilterService {

  private analogFiltersUrl: string;

  constructor(private http: HttpClient) {
    this.analogFiltersUrl = 'http://localhost:8080/api/v1/active-analog-filters/supported-filter-types';
  }

  public findAll(): Observable<string[]> {
    return this.http.get<string[]>(this.analogFiltersUrl);
  }
}
