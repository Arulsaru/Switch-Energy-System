import { NgModule } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';


const materialComponents = [
    MatIconModule
]

@NgModule({
  imports: [materialComponents],
  exports: [materialComponents],
})
export class MaterialModule {}
