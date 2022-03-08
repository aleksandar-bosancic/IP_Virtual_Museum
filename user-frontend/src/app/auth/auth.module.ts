import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AuthRoutingModule } from './auth-routing.module';
import { AppMaterialModule } from '../app-material/app-material.module';
import { LoginDialogComponent } from "./login-dialog/login-dialog.component";
import { RegisterComponent } from './register/register.component';


@NgModule({
  declarations: [
    LoginDialogComponent,
    RegisterComponent
  ],
  imports: [
    CommonModule,
    AuthRoutingModule,
    AppMaterialModule
  ]
})
export class AuthModule { }
