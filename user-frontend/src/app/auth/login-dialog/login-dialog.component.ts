import {Component, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MatDialogRef} from "@angular/material/dialog";
import {AuthService} from "../services/auth.service";
import {Login} from "../../model/login.model";
import {Router} from "@angular/router";
import {LogService} from "../../service/log.service";
import {environment} from "../../../environments/environment";

@Component({
  selector: 'app-login-dialog',
  templateUrl: './login-dialog.component.html',
  styleUrls: ['./login-dialog.component.css']
})
export class LoginDialogComponent implements OnInit {
  @Output() public loggedIn: boolean = false;
  hide: boolean = true;
  public loginFormGroup: FormGroup = new FormGroup({});

  constructor(private formBuilder: FormBuilder, public dialogReference: MatDialogRef<LoginDialogComponent>,
              private service: AuthService, private router: Router, private logService: LogService) {
  }

  ngOnInit(): void {
    this.loginFormGroup = this.formBuilder.group({
      username: [null, Validators.required],
      password: [null, Validators.required]
    })
  }

  login(): void {
    let username = this.loginFormGroup.get('username')?.value;
    let password = this.loginFormGroup.get('password')?.value;
    this.service.login(new Login(username, password)).subscribe(response => {
        this.service.updateCredentials(username, response.key);
        this.logService.log(environment.infoCategory, 'login');
        this.router.navigate(['/']).then();
        this.dialogReference.close();
      },
      error => {
        alert('Login unsuccessful.');
        this.loginFormGroup.reset();
      });
  }
}
