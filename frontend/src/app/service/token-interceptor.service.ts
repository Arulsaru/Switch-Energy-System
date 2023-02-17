import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
})

export class TokenInterceptorService implements HttpInterceptor {
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        console.log(req.url);

        let jwtToken: HttpRequest<any>;

        if(req.url === 'http://localhost:8080/user/login-get-token' || req.url === 'http://localhost:8080/user/signup') {
            jwtToken = req.clone({
                setHeaders: {
                    Authorization: ``
                }
            })
        } else {
            let token = sessionStorage.getItem('token');        
            jwtToken = req.clone({
                setHeaders: {
                    Authorization: `Bearer ${token}`
                }
            })
        }
        return next.handle(jwtToken);
    }
}