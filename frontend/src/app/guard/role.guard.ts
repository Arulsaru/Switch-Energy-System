import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})

export class RoleGuard implements CanActivate {

    constructor(private route: Router) {}

    canActivate(): boolean {
        let role = sessionStorage.getItem('role');
        if (role === 'ADMIN') {
            return true;
        }
        alert('you dont have admin rights');    
        this.route.navigate(['/auth/login']);
        return false;
    }
}