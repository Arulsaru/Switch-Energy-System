import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { providerType } from '../interface/provider';
import { createProviderType } from '../interface/provider';
import { baseURL } from '../constant/constant-variables';

@Injectable({
    providedIn: 'root'
})

export class ProviderService {
    constructor(private http: HttpClient) {}

    getAllProviders(page: number, count: number): Observable<providerType[]> {
        return this.http.get<providerType[]>(`${baseURL}provider/get-all-providers?pageNumber=${page}&pageSize=${count}`);
    }

    getAllEnabledProviders(): Observable<providerType[]> {
        return this.http.get<providerType[]>(`${baseURL}provider/get-all-enabled-providers`);
    }

    createProviders(newProvider: createProviderType): Observable<Object> {
        return this.http.post(`${baseURL}provider`, newProvider);
    }

    enableProvider(providerName: String): Observable<String> {
        return this.http.put<String>(`${baseURL}provider/enable/${providerName}`, null);
    }

    disableProvider(providerName: String): Observable<String> {
        return this.http.put<String>(`${baseURL}provider/disable/${providerName}`, null);
    }
}