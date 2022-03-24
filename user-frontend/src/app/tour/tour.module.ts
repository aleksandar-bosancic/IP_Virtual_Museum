import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TourRoutingModule } from './tour-routing.module';
import { TourComponent } from './tour/tour.component';
import {AppMaterialModule} from "../app-material/app-material.module";


@NgModule({
  declarations: [
    TourComponent
  ],
  imports: [
    CommonModule,
    TourRoutingModule,
    AppMaterialModule
  ]
})
export class TourModule { }
