import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {path: '', redirectTo: 'user', pathMatch: 'full'},
  {
    path: 'auth',
    loadChildren:() => import('./auth/auth.module').then(x => x.AuthModule)
  },
  {
    path: 'admin',
    loadChildren:() => import('./admin/admin.module').then(x => x.AdminModule)
  },
  {
    path: 'user',
    loadChildren:() => import('./user/user.module').then(x => x.UserModule)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
