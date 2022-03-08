import {Component, OnInit, Output} from '@angular/core';
import {MatDialog} from "@angular/material/dialog";
import {LoginDialogComponent} from "../../auth/login-dialog/login-dialog.component";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AuthService} from "../../auth/services/auth.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(public http: HttpClient) { }

  ngOnInit(): void {
  }

  send() {
    let header = { headers: new HttpHeaders().set('Authorization', 'Digest ' + localStorage.getItem('token'))}
    this.http.get("http://localhost:9000/test", header).subscribe();
  }
}
