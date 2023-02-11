import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { providerType } from '../interface/providerType';

@Injectable({
    providedIn: 'root'
})

export class UserService {
    baseURL: String = 'http://localhost:8080/';
    constructor(private http: HttpClient) {}

    getAllProviders(): Observable<providerType[]> {
        return this.http.get<providerType[]>(`${this.baseURL}user/get_all_providers`);
    }
}