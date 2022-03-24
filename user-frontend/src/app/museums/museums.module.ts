import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MuseumsRoutingModule } from './museums-routing.module';
import { MuseumsComponent } from './museums/museums.component';
import { AppMaterialModule } from '../app-material/app-material.module';
import { MuseumDetailsComponent } from './museum-details/museum-details.component';


@NgModule({
  declarations: [
    MuseumsComponent,
    MuseumDetailsComponent
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
