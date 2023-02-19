import { Component, OnInit, Inject } from '@angular/core';
import { providerType } from 'src/app/shared/interface/provider';
import { ProviderService } from 'src/app/shared/service/provider.service';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { UserSmartMeterComponent } from '../user-smart-meter/user-smart-meter.component';
import { FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-create-smart-meter-dialog',
  templateUrl: './create-smart-meter-dialog.component.html',
  styleUrls: ['./create-smart-meter-dialog.component.css'],
})
export class CreateSmartMeterDialogComponent {
  providersList: providerType[] = [];
  selectedProviderName: String = 'this.providersList[0].providerName;';
  selectProviderControl = new FormControl('', [Validators.required]);

  constructor(
    private providerService: ProviderService,
    public dialogRef: MatDialogRef<UserSmartMeterComponent>,
    @Inject(MAT_DIALOG_DATA)
    public data: String
  ) {}

  ngOnInit(): void {
    this.providerService.getAllEnabledProviders().subscribe({
      next: (response) => this.providersList = response
    });
  }

  cancelDialog(): void {
    this.dialogRef.close();
  }
}
