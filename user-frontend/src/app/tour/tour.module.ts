import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TourRoutingModule } from './tour-routing.module';
import { TourComponent } from './tour/tour.component';
import {AppMaterialModule} from "../app-material/app-material.module";
import { TourPurchaseComponent } from './tour-purchase/tour-purchase.component';


@NgModule({
  declarations: [
    TourComponent,
    TourPurchaseComponent
  ],
  imports: [
    CommonModule,
    TourRoutingModule,
    AppMaterialModule
  ]
})
export class TourModule { }
