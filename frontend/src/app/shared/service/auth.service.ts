import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root'
})

export class AuthService {
    constructor() {}
    isLoggedIn(): boolean {
        return !(sessionStorage.getItem('token') === null);
    }

    isTokenExpired(token: string): boolean {
        const expiry = (JSON.parse(atob(token.split('.')[1]))).exp;
        return (Math.floor((new Date).getTime() / 1000)) >= expiry;
    }   
}