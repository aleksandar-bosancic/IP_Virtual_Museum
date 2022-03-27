import { Component } from '@angular/core';
import {LoginDialogComponent} from "./auth/login-dialog/login-dialog.component";
import {MatDialog} from "@angular/material/dialog";
import {AuthService} from "./auth/services/auth.service";
import {ActivatedRoute, Router} from "@angular/router";
import {LogService} from "./service/log.service";
import {environment} from "../environments/environment";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'user-frontend';

  constructor(public loginDialog: MatDialog, private service: AuthService,
              private router: Router, private route: ActivatedRoute,
              private logService: LogService) {
  }

  isLoggedIn(): boolean {
    return this.service.loggedIn;
  }

  logout(): void {
    this.logService.log(environment.infoCategory, "logout");
    this.service.logout();
    this.router.navigate(['/'], {relativeTo: this.route}).then();
  }

  openLogin(): void {
    let dialogReference = this.loginDialog.open(LoginDialogComponent, {

    });
    dialogReference.afterClosed().subscribe( result =>{
    })
  }
}
