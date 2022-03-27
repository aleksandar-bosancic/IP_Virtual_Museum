import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {TourComponent} from "./tour/tour.component";
import {TourPurchaseComponent} from "./tour-purchase/tour-purchase.component";

const routes: Routes = [
  {
    path: '',
    component: TourComponent
  },
  {
    path: 'tour-purchase/:id',
    component: TourPurchaseComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TourRoutingModule { }
