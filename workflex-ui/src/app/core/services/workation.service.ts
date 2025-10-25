import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Workation } from '../models/workation.model';

@Injectable({ providedIn: 'root' })
export class WorkationService {
  private apiUrl = 'http://localhost:8080/workflex/workation';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Workation[]> {
    return this.http.get<Workation[]>(this.apiUrl);
  }
}
