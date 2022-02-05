import { NgModule } from '@angular/core';
import { MatAutocompleteModule } from '@angular/material/autocomplete'
import { MatCardModule } from '@angular/material/card'

const materialModules = [
  MatAutocompleteModule,
  MatCardModule
];

@NgModule({
  declarations: [],
  imports: [
    ...materialModules
  ],
  exports: [
    ...materialModules
  ],
})
export class AppMaterialModule { }
