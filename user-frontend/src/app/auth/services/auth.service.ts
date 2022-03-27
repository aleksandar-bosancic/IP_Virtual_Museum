import {Injectable} from '@angular/core';
import {Login} from "../../model/login.model";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Register} from "../../model/register.model";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private token: string = 'token';
  private logged: string = 'isLoggedIn';
  public username: string = '';
  public loggedIn: boolean = this.isLoggedIn();


  constructor(private http: HttpClient) {
  }
  //TODO napravi autorizaciju za sve requeste

  public isLoggedIn(): boolean {
    return localStorage.getItem('isLoggedIn') === 'true';
  }

  updateCredentials(username: string, key: string){
    this.username = username;
    localStorage.setItem('username', username);
    localStorage.setItem(this.token, key);
    localStorage.setItem(this.logged, 'true');
    this.loggedIn = true;
  }

  public login(userLogin: Login) {
    const headers = {'Content-Type': 'application/json'}
    return this.http.post<any>(environment.apiURL + '/login', JSON.stringify(userLogin), {'headers': headers});
  }

  register(userRegister: Register) {
    const headers = {'Content-Type': 'application/json'}
    return this.http.post<any>(environment.apiURL + '/register', JSON.stringify(userRegister), {'headers': headers});
  }

  logout(): void {
    localStorage.removeItem(this.token);
    localStorage.removeItem(this.logged);
    this.loggedIn = false;
  }
}
