import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { Observable } from "rxjs";
import { AuthService } from "./auth.service";

@Injectable({
    providedIn: 'root'
})

export class TokenInterceptorService implements HttpInterceptor {
    constructor(private authService: AuthService, private route: Router) {}

    intercept(req: HttpRequest<URL>, next: HttpHandler): Observable<HttpEvent<any>> {
        
        let jwtToken: HttpRequest<URL>;

        if(req.url === 'http://localhost:8080/user/login-get-token' || req.url === 'http://localhost:8080/user/signup') {
            jwtToken = req.clone({
                setHeaders: {
                    Authorization: ``
                }
            })
        } else {
            let token = sessionStorage.getItem('token');        
            if(this.authService.isTokenExpired(JSON.stringify(token))) {
                alert('login again.. token expired');
                this.route.navigate(['./auth/login']);
            } 
            jwtToken = req.clone({
                setHeaders: {
                    Authorization: `Bearer ${token}`
                }
            }) 
        }   
        return next.handle(jwtToken);
    }
}