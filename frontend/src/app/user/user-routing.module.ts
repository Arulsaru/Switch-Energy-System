import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SmartMeterReadingComponent } from './smart-meter-reading/smart-meter-reading.component';
import { UserSmartMeterComponent } from './user-smart-meter/user-smart-meter.component';

const routes: Routes = [
  { path: '', redirectTo: 'smart-meter', pathMatch: 'full' },
  { path: 'smart-meter', component: UserSmartMeterComponent },
  { path: ':smartMeterId/view-readings', component: SmartMeterReadingComponent }
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class UserRoutingModule {}
