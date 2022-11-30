import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {HttpClient} from'@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CalculationsService {
  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient) { }
  public clear(): Observable<String> {
    return this.http.get(`${this.apiServerUrl}/clear`, {responseType: 'text'});
  }
  public solveFunction(type: String,num1:String,num2:String): Observable<String> {
    return this.http.get(`${this.apiServerUrl}/solveFunction/${type}/${num1}/${num2}`, {responseType: 'text'});
  }
  public delete(num: String):Observable<String> {
    return this.http.get(`${this.apiServerUrl}/delete/${num}`, {responseType: 'text'});
  }
  public addDigit(num: String, digit: String): Observable<String> {
    return this.http.get(`${this.apiServerUrl}/addDigit/${num}/${digit}`,{responseType: 'text'});
  }

}
