import { Component, Inject } from '@angular/core';
import { ProvidersPageComponent } from '../providers-page/providers-page.component';
import { createProviderType } from '../interface/createProvider';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-create-provider-dialog',
  templateUrl: './create-provider-dialog.component.html',
  styleUrls: ['./create-provider-dialog.component.css'],
})
export class CreateProviderDialogComponent {
  newProvider: createProviderType = {
    providerName: "",
    ratePerWatt: 0
  }
  constructor(
    public dialogRef: MatDialogRef<ProvidersPageComponent>,
    @Inject(MAT_DIALOG_DATA)
    public data: createProviderType
  ) {}

  cancelDialog(): void {
    this.dialogRef.close();
  }
}
