import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { providerType } from '../interface/provider';
import { createProviderType } from '../interface/create-provider';

@Injectable({
    providedIn: 'root'
})

export class ProviderService {
    baseURL: String = 'http://localhost:8080/';
    constructor(private http: HttpClient) {}

    getAllProviders(): Observable<providerType[]> {
        return this.http.get<providerType[]>(`${this.baseURL}provider/get-all-providers`);
    }

    createProviders(newProvider: createProviderType): Observable<Object> {
        return this.http.post(`${this.baseURL}provider`, newProvider);
    }

    enableProvider(providerName: String): Observable<String> {
        return this.http.put<String>(`${this.baseURL}provider/enable/${providerName}`, null);
    }

    disableProvider(providerName: String): Observable<String> {
        return this.http.put<String>(`${this.baseURL}provider/disable/${providerName}`, null);
    }
}