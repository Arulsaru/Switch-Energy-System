import { Component, OnInit } from '@angular/core';
import { pageNumber, pageSize } from 'src/app/shared/constant/constant-variables';
import { smartMeterType } from '../../shared/interface/smart-meter';
import { SmartMeterService } from '../../shared/service/smart-meter.service';

@Component({
  selector: 'app-smart-meter',
  templateUrl: './admin-smart-meter.component.html',
  styleUrls: ['./admin-smart-meter.component.css'],
})

export class AdminSmartMeterComponent implements OnInit {
  smartMeterList: smartMeterType[] = [];
  page: number = pageNumber;
  count: number = pageSize;

  constructor(private service: SmartMeterService) {}

  ngOnInit() {
    this.getSmartMeterList();
  }

  getSmartMeterList(): void {
    this.service.getPendingSmartMeters(this.page, this.count).subscribe({
      next: (response: smartMeterType[]) => this.smartMeterList = response
    });
  }

  approveSmartMeter(smartMeter: smartMeterType): void {
    this.service.approveOrRejectSmartMeter(smartMeter.smartMeterId, 'APPROVED', smartMeter.providerName)
                .subscribe({ next: () => this.getSmartMeterList() });
  }

  rejectSmartMeter(smartMeter: smartMeterType): void {
    this.service.approveOrRejectSmartMeter(smartMeter.smartMeterId, 'REJECTED', smartMeter.providerName)
                .subscribe({ next: () => this.getSmartMeterList() })
  }
}
