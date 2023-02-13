import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminSmartMeterComponent } from './admin-smart-meter/admin-smart-meter.component';
import { CreateProviderDialogComponent } from './create-provider-dialog/create-provider-dialog.component';
import { ProvidersPageComponent } from './providers-page/providers-page.component';

const routes: Routes = [
  { path: '', redirectTo: 'smart-meter', pathMatch: 'full' },
  { path: 'smart-meter', component: AdminSmartMeterComponent },
  { path: 'provider', component: ProvidersPageComponent },
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AdminRoutingModule {}
