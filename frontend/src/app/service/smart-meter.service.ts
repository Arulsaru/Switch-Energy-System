import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { smartMeterType } from '../interface/smart-meter';
import { Observable } from 'rxjs';
import { baseURL } from '../constant/constant-variables';
import { userType } from '../interface/user';

@Injectable({
  providedIn: 'root',
})
export class SmartMeterService {
  constructor(private http: HttpClient) {}

  getPendingSmartMeters(): Observable<smartMeterType[]> {
    return this.http.get<smartMeterType[]>(`${baseURL}smartmeter`);
  }

  approveOrRejectSmartMeter(
    smartMeterId: String,
    smartMeterStatus: String,
    providerName: String
  ): Observable<Object> {
    return this.http.put(
      `${baseURL}smartmeter/${smartMeterId}/approve-or-reject/${smartMeterStatus}/${providerName}`,
      null
    );
  }

  createSmartMeter(providerName: String) {
    return this.http.post(
      `${baseURL}smartmeter/Arulmozhi/create/${providerName}`,
      null
    );
  }

  getSmartMeterByUserName(userName: String): Observable<smartMeterType[]> {
    return this.http.get<smartMeterType[]>(`${baseURL}smartmeter/${userName}`);
  }
}
