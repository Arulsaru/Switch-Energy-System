import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginPageComponent } from './login-page/login-page.component';
import { MainPageComponent } from './main-page/main-page.component';
import { SignupPageComponent } from './signup-page/signup-page.component';
import { AdminSmartMeterComponent } from './admin-smart-meter/admin-smart-meter.component';
import { ProvidersPageComponent } from './providers-page/providers-page.component';

const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginPageComponent },
  { path: 'signup', component: SignupPageComponent },
  { path: 'main', component: MainPageComponent },
  { path: 'smart-meter', component: AdminSmartMeterComponent },
  { path: 'providers', component: ProvidersPageComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
