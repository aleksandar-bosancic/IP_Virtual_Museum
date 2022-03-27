import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Register} from "../../model/register.model";
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  public hide: boolean = true;
  registerFormGroup: FormGroup = new FormGroup({});

  constructor(private formBuilder: FormBuilder, private service: AuthService, private router: Router) {
  }

  ngOnInit(): void {
    this.registerFormGroup = this.formBuilder.group({
      username: [null, Validators.required],
      password: [null, Validators.required],
      repeatPassword: [null, Validators.required],
      email: [null, Validators.required],
      firstName: [null, Validators.required],
      lastName: [null, Validators.required]
    })
  }

  register() {
    let username = this.registerFormGroup.get('username')?.value;
    let password = this.registerFormGroup.get('password')?.value;
    let repeatPassword = this.registerFormGroup.get('repeatPassword')?.value;
    let email = this.registerFormGroup.get('email')?.value;
    let firstName = this.registerFormGroup.get('firstName')?.value;
    let lastName = this.registerFormGroup.get('lastName')?.value;
    if (!this.validateUsername(username)) {
      alert('Username must be 12 characters long and must not contain special characters.');
    } else if (!this.validatePassword(password)) {
      alert('Password muse be at least 15 characters long and must contain lowercase, uppercase letters and number.');
    } else {
      if (password != repeatPassword) {
        alert('Passwords must match.');
        this.registerFormGroup.reset();
      } else {
        this.service.register(new Register(username, password, firstName, lastName, email)).subscribe(response => {
          this.router.navigate(['/']).then(value => {
            console.log(value)
          });
        }, error => {
          alert('Registration was not successful, please try again.');
          this.registerFormGroup.reset();
        });
      }
    }
  }

  validateUsername(username: string) {
    let hasSpecialCharacters = /[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]+/.test(username);
    return !hasSpecialCharacters && username.length >= 12;
  }

  validatePassword(password: string) {
    let hasNumber = /\d/.test(password);
    let hasUpper = /[A-Z]/.test(password);
    let hasLower = /[a-z]/.test(password);
    console.log(hasNumber + " " + hasUpper + "  " + hasLower  )
    return hasLower && hasUpper && hasNumber && password.length >= 15;
  }
}
