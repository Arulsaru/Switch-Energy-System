import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { smartMeterReading, smartMeterType, totalReadings } from 'src/app/shared/interface/smart-meter';
import { SmartMeterService } from 'src/app/shared/service/smart-meter.service';
import { UserService } from 'src/app/shared/service/user.service';
import { CreateSmartMeterDialogComponent } from '../create-smart-meter-dialog/create-smart-meter-dialog.component';

@Component({
  selector: 'app-user-smart-meter',
  templateUrl: './user-smart-meter.component.html',
  styleUrls: ['./user-smart-meter.component.css'],
})

export class UserSmartMeterComponent {

  smartMeters: smartMeterType[] = [];
  totalReadings: number = 0;
  totalAmount: number = 0;
  selectedProvider = '';
  smartMeterReadings: smartMeterReading = {
    smartMeterId: '',
    dateAndTime: '',
    epochTime: 0
  }
  userName: string | null = '';
  isSmartMeterCreateRequestSent: boolean = false;

  constructor(
    private smartMeterService: SmartMeterService,
    public dialog: MatDialog
  ) { }

  ngOnInit() {
    this.userName = sessionStorage.getItem('name');
    this.getAllSmartMeter(JSON.stringify(this.userName)); // string object ah maathii slice pandree
    // this.getAllSmartMeter();
  }

  getAllSmartMeter(userName: String): void {
    userName = userName.slice(3, userName.length - 3); // to remove double quotes
    this.smartMeterService.getAllApprovedSmartMeterByUserName(userName).subscribe({
      next: (res) => this.smartMeters = res
    })
    console.log(this.smartMeters);
  }

  createSmartMeter(): void {
    const dialogRef = this.dialog.open(CreateSmartMeterDialogComponent, {
      data: {
        selectedProvider: this.selectedProvider,
      },
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result != null) {
        this.selectedProvider = result;
        this.smartMeterService
          .createSmartMeter(JSON.stringify(this.userName),this.selectedProvider)
          .subscribe({
            next: () => {
              this.isSmartMeterCreateRequestSent = true;
              window.setTimeout(() => {
                this.isSmartMeterCreateRequestSent = false;
              }, 3000);
            }
          })
      }
    });
  }

  switchProvider(smartMeterId: String): void {
    const dialogRef = this.dialog.open(CreateSmartMeterDialogComponent, {
      data: {
        selectedProvider: this.selectedProvider,
      },
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result != null) {
        this.selectedProvider = result;
        this.smartMeterService
          .switchProvider(smartMeterId, this.selectedProvider)
          .subscribe({
            next: () => console.log("smart meter switched"),
            error: () => { }
          })
      }
    });
    this.getAllSmartMeter(JSON.stringify(this.userName));
  }

  calculateReadings(smartMeterId: String): void {
    this.smartMeterService.calculateReadings(smartMeterId).subscribe({
      next: (res: number) => this.totalReadings = res
    })
  }

  calculateAmount(smartMeterId: String, providerName: String): void {
    this.smartMeterService.getCalculatedAmount(smartMeterId, providerName).subscribe({
      next: (res: number) => {
        this.totalAmount = res
        this.calculateReadings(smartMeterId);
      }
    })
  }
}
