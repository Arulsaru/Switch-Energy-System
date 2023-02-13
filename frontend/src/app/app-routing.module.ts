import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthComponent } from './auth/auth.component';
import { AdminSmartMeterComponent } from './admin/admin-smart-meter/admin-smart-meter.component';
import { ProvidersPageComponent } from './admin/providers-page/providers-page.component';
import { AdminComponent } from './admin/admin.component';

const routes: Routes = [
  {path: '', redirectTo: 'admin', pathMatch: 'full'},
  {
    path: 'auth',
    component:AuthComponent,
    loadChildren:() => import('./auth/auth.module').then(x => x.AuthModule)
  },
  {
    path: 'admin',
    component: AdminComponent,
    loadChildren:() => import('./admin/admin.module').then(x => x.AdminModule)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
