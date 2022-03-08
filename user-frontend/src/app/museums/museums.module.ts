import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MuseumsRoutingModule } from './museums-routing.module';
import { MuseumsComponent } from './museums/museums.component';
import { AppMaterialModule } from '../app-material/app-material.module';


@NgModule({
  declarations: [
    MuseumsComponent
  ],
  imports: [
    CommonModule,
    MuseumsRoutingModule,
    AppMaterialModule
  ],
  exports: [
    MuseumsComponent
  ]
})
export class MuseumsModule { }
