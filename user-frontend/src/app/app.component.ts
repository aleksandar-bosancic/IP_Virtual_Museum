import { Component } from '@angular/core';
import {LoginDialogComponent} from "./auth/login-dialog/login-dialog.component";
import {MatDialog} from "@angular/material/dialog";
import {AuthService} from "./auth/services/auth.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'user-frontend';

  constructor(public loginDialog: MatDialog, private service: AuthService, private router: Router, private route: ActivatedRoute) {
  }

  isLoggedIn(): boolean {
    return this.service.loggedIn;
  }

  logout(): void {
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

//TODO: Napravi back dugme koje koje ce biti aktivno van home stranice
