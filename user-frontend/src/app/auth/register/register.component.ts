import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  public hide: boolean = true;
  registerFormGroup: FormGroup = new FormGroup({});

  constructor(private formBuilder: FormBuilder) { }

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


  }
}
