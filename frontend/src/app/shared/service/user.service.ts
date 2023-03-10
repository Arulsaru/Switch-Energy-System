import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { userType } from '../interface/user';
import { baseURL } from '../constant/constant-variables';

@Injectable({
    providedIn: 'root'
})

export class UserService {
    constructor(private http: HttpClient) {}

    userName: String = new String(sessionStorage.getItem('userName'));

    createUser(user: Object): Observable<Object> {
        return this.http.post(`${baseURL}user/signup`, user);
    }

    generateToken(user: Object): Observable<Object> {
        return this.http.post(`${baseURL}user/login-get-token`, user);
    }

    getUserByName(userName: String | null | undefined): Observable<userType> {
        return this.http.get<userType>(`${baseURL}user/${userName}`);
    }
}