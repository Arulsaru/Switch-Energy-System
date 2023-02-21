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

  getPendingSmartMeters(page: number, count: number): Observable<smartMeterType[]> {
    return this.http.get<smartMeterType[]>(`${baseURL}smartmeter?pageNumber=${page}&pageSize=${count}`);
  }

  approveOrRejectSmartMeter(smartMeterId: String, smartMeterStatus: String, providerName: String
  ): Observable<Object> {
    return this.http.put(
      `${baseURL}smartmeter/${smartMeterId}/approve-or-reject/${smartMeterStatus}/${providerName}`,
      null
    );
  }

  createSmartMeter(userName: String, providerName: String) {
    userName = userName.slice(3, userName.length - 3);
    return this.http.post(
      `${baseURL}smartmeter/${userName}/create/${providerName}`,
      null
    );
  }

  getAllApprovedSmartMeterByUserName(userName: String): Observable<smartMeterType[]> {
    return this.http.get<smartMeterType[]>(`${baseURL}smartmeter/${userName}`);
  }

  switchProvider(smartMeterId: String, providerName: String) {
    return this.http.put(`${baseURL}smartmeter/${smartMeterId}/switch-providers/${providerName}`, null);
  }

  getCalculatedAmount(smartMeterId: String, providerName: String): Observable<number> {
    return this.http.get<number>(`${baseURL}smartmeter/${smartMeterId}/calculate-amount/${providerName}`);
  }

  calculateReadings(smartMeterId: String): Observable<number> {
    return this.http.get<number>(`${baseURL}readings/smart-meter/${smartMeterId}`)
  }
}
