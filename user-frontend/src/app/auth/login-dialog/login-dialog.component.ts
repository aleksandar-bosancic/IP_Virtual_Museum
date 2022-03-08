import {Component, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MatDialogRef} from "@angular/material/dialog";
import {AuthService} from "../services/auth.service";
import {Login} from "../../model/login.model";
import {Router} from "@angular/router";

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
              private service: AuthService, private router: Router) { }

  ngOnInit(): void {
    this.loginFormGroup = this.formBuilder.group({
      username: [null, Validators.required],
      password: [null, Validators.required]
    })
  }

  login(): void {
    let username = this.loginFormGroup.get('username')?.value;
    let password = this.loginFormGroup.get('password')?.value;
    if(this.service.login(new Login(username,password))){
      this.router.navigate(['/']).then();
      this.dialogReference.close();
    }
  }

}
