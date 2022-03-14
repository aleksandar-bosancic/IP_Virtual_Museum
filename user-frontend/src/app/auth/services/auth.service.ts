import { Injectable } from '@angular/core';
import { Login } from "../../model/login.model";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private token: string = 'token';
  private logged: string = 'isLoggedIn';
  public loggedIn: boolean = this.isLoggedIn();

  public isLoggedIn(): boolean {
    return localStorage.getItem('isLoggedIn') === 'true';
  }

  public login(userLogin: Login): boolean {
    const headers = { 'Content-Type': 'application/json'}
    this.http.post<any>(environment.apiURL + '/login', JSON.stringify(userLogin), {'headers': headers}).subscribe(res => {
      localStorage.setItem(this.token, res.key);
      localStorage.setItem(this.logged, 'true');
      this.loggedIn = true;
    })
    return true;
  }

  constructor(private http: HttpClient) { }

  logout(): void {
    localStorage.removeItem(this.token);
    localStorage.removeItem(this.logged);
    this.loggedIn = false;
  }
}
