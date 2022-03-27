import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {LogEntry} from "../model/log-entry.model";
import {AuthService} from "../auth/services/auth.service";
import {Subject, takeUntil} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class LogService {

  constructor(private http: HttpClient, private authService: AuthService) {
  }

  public log(category: string, label: string) {
    if (this.authService.username != '') {
      let log = new LogEntry(this.authService.username, Date.now().toString(), category, label);
      this.http.post(environment.apiURL + '/log', log).subscribe();
    }
  }
}
