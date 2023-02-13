import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { smartMeterType } from '../interface/smart-meter';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})

export class SmartMeterService {
    constructor(private http: HttpClient) {}

    baseURL: String = 'http://localhost:8080/'; 

    getPendingSmartMeters(): Observable<smartMeterType[]> {
        return this.http.get<smartMeterType[]>(`${this.baseURL}smartmeter`);
    }
    approveOrRejectSmartMeter(smartMeterId: String, smartMeterStatus: String, providerName: String): Observable<Object> {
        return this.http.put(`${this.baseURL}smartmeter/${smartMeterId}/approve-or-reject/${smartMeterStatus}/${providerName}`, null);
    }
}