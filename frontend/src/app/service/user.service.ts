import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { userType } from '../interface/user';

@Injectable({
    providedIn: 'root'
})

export class UserService {
    constructor(private http: HttpClient) {}

    baseURL: String = 'http://localhost:8080/'; 

    createUser(user: Object): Observable<Object> {
        return this.http.post(`${this.baseURL}user`, user);
    }

    getUserByName(userName: String): Observable<userType> {
        return this.http.get<userType>(`${this.baseURL}user`);
    }
}