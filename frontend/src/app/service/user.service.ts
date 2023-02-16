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

    createUser(user: Object): Observable<Object> {
        return this.http.post(`${baseURL}user`, user);
    }

    generateToken(user: Object): Observable<Object> {
        return this.http.post(`${baseURL}user/authenticate`, user);
    }

    getUserByName(userName: String): Observable<userType> {
        return this.http.get<userType>(`${baseURL}user\${userName}`);
    }
}