import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { smartMeterType } from '../interface/smart-meter';
import { Observable } from 'rxjs';
import { baseURL } from '../constant/constant-variables';

@Injectable({
    providedIn: 'root'
})

export class SmartMeterService {
    constructor(private http: HttpClient) {}

    getPendingSmartMeters(): Observable<smartMeterType[]> {
        return this.http.get<smartMeterType[]>(`${baseURL}smartmeter`);
    }

    approveOrRejectSmartMeter(smartMeterId: String, smartMeterStatus: String, providerName: String): Observable<Object> {
        return this.http.put(`${baseURL}smartmeter/${smartMeterId}/approve-or-reject/${smartMeterStatus}/${providerName}`, null);
    }
}