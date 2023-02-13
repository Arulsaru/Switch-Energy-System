import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})

export class UserService {
    constructor(private http: HttpClient) {}

    baseURL: String = 'http://localhost:8080/'; 

    createUser(user: Object): Observable<Object> {
        return this.http.post(`${this.baseURL}user`, user);
    }
}