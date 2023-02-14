import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { SmartMeterService } from 'src/app/service/smart-meter.service';
import { CreateSmartMeterDialogComponent } from '../create-smart-meter-dialog/create-smart-meter-dialog.component';

@Component({
  selector: 'app-user-smart-meter',
  templateUrl: './user-smart-meter.component.html',
  styleUrls: ['./user-smart-meter.component.css'],
})
export class UserSmartMeterComponent {
  constructor(
    private smartMeterService: SmartMeterService,
    public dialog: MatDialog
  ) {}

  ngOnInit() {
    // this.service.getUserByName("logged in username").subscribe((res) => {
    //     console.log(res);
    // })
  }

  selectedProvider = '';

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
          .subscribe((result) => {
            console.log('smart meter created');
          });
      }
    });
  }
}
