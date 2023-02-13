import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { smartMeterType } from '../interface/smart-meter-type';
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
    approveOrRejectSmartMeter(smartMeterId: String, smartMeterStatus: String) {
        return this.http.put(`${this.baseURL}smartmeter/${smartMeterId}/approve_or_reject/${smartMeterStatus}`, null);
    }
}