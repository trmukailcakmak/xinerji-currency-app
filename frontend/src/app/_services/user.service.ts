import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';

const API_URL = 'http://localhost:8080/api/';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private http: HttpClient) { }

  getCurrencyByDate(date: any): Observable<any> {
    return this.http.post(API_URL + 'currency/getCurrencyRateByDate', { date }, httpOptions);
  }

  getUserBoard(email: string): Observable<any> {
    return this.http.get(API_URL + 'user/user/' + email, { responseType: 'text' });
  }
}
