import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { smartMeterReading, smartMeterType } from 'src/app/interface/smart-meter';
import { SmartMeterService } from 'src/app/service/smart-meter.service';
import { UserService } from 'src/app/service/user.service';
import { CreateSmartMeterDialogComponent } from '../create-smart-meter-dialog/create-smart-meter-dialog.component';

@Component({
  selector: 'app-user-smart-meter',
  templateUrl: './user-smart-meter.component.html',
  styleUrls: ['./user-smart-meter.component.css'],
})

export class UserSmartMeterComponent {

  smartMeters: smartMeterType[] = [];
  selectedProvider = '';
  smartMeterReadings: smartMeterReading = {
    smartMeterId: '',
    dateAndTime: '',
    epochTime: 0
  }

  constructor(
    private smartMeterService: SmartMeterService,
    private userService: UserService,
    public dialog: MatDialog
  ) {}

  ngOnInit() {
    this.getAllSmartMeter(this.userService.userName);
  }

  getAllSmartMeter(userName: String): void {
    this.smartMeterService.getAllApprovedSmartMeterByUserName(userName).subscribe({
      next: (res) => this.smartMeters = res
    })
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
          .createSmartMeter(this.selectedProvider)
          .subscribe({
            next: () => console.log("smart meter created")
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
            next: () => console.log("smart meter created")
          })
      }
    });
    this.getAllSmartMeter("Arulmozhi"); 
  }
}
