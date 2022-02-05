import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MuseumsRoutingModule } from './museums-routing.module';
import { MuseumsComponent } from './museums/museums.component';


@NgModule({
  declarations: [
    MuseumsComponent
  ],
  imports: [
    CommonModule,
    MuseumsRoutingModule
  ]
})
export class MuseumsModule { }
