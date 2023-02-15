import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { smartMeterReading, smartMeterType } from 'src/app/interface/smart-meter';
import { SmartMeterService } from 'src/app/service/smart-meter.service';
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
    public dialog: MatDialog,
    private router: Router
  ) {}

  ngOnInit() {
    this.smartMeterService.getAllApprovedSmartMeterByUserName("Arulmozhi").subscribe((res) => {
      this.smartMeters = res;
      console.log(this.smartMeters);
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

  redirectToViewReadingsPage(smartMeterId: String): void {
    this.router.navigateByUrl(`user/${smartMeterId}/view-readings`);
  }
}
