import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserSmartMeterComponent } from './user-smart-meter/user-smart-meter.component';

const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'smart-meter', component: UserSmartMeterComponent },
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AuthRoutingModule {}
