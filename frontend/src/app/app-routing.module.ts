import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './auth/guard/auth.guard';
import { RoleGuard } from './auth/guard/role.guard';

const routes: Routes = [
  {path: '', redirectTo: 'auth', pathMatch: 'full'},
  {
    path: 'auth',
    loadChildren:() => import('./auth/auth.module').then(x => x.AuthModule)
  },
  {
    path: 'admin',
    loadChildren:() => import('./admin/admin.module').then(x => x.AdminModule), 
    canActivate: [AuthGuard],
    data: {
      role: 'ADMIN'
    }
  },
  {
    path: 'user',
    loadChildren:() => import('./user/user.module').then(x => x.UserModule), 
    canActivate: [AuthGuard],
    data: {
      role: 'USER'
    }
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
