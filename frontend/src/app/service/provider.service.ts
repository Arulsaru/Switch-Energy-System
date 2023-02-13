import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { providerType } from '../interface/providerType';

@Injectable({
    providedIn: 'root'
})

export class ProviderService {
    baseURL: String = 'http://localhost:8080/';
    constructor(private http: HttpClient) {}

    getAllProviders(): Observable<providerType[]> {
        return this.http.get<providerType[]>(`${this.baseURL}provider/get_all_providers`);
    }

    enableProvider(providerName: String): Observable<String> {
        return this.http.put<String>(`${this.baseURL}provider/enable/${providerName}`, null);
    }

    disableProvider(providerName: String): Observable<String> {
        return this.http.put<String>(`${this.baseURL}provider/disable/${providerName}`, null);
    }
}