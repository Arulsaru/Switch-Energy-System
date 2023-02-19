import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './guard/auth.guard';
import { RoleGuard } from './guard/role.guard';

const routes: Routes = [
  {path: '', redirectTo: 'auth', pathMatch: 'full'},
  {
    path: 'auth',
    loadChildren:() => import('./auth/auth.module').then(x => x.AuthModule)
  },
  {
    path: 'admin',
    loadChildren:() => import('./admin/admin.module').then(x => x.AdminModule), 
    canActivate: [AuthGuard, RoleGuard]
  },
  {
    path: 'user',
    loadChildren:() => import('./user/user.module').then(x => x.UserModule), 
    canActivate: [AuthGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
