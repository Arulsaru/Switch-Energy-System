import { Component, Inject } from '@angular/core';
import { ProvidersPageComponent } from '../providers-page/providers-page.component';
import { createProviderType } from 'src/app/interface/provider';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { FormBuilder, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-create-provider-dialog',
  templateUrl: './create-provider-dialog.component.html',
  styleUrls: ['./create-provider-dialog.component.css'],
})
export class CreateProviderDialogComponent {
  newProvider: createProviderType = {
    providerName: '',
    ratePerWatt: 0,
  };
  
  constructor(
    public dialogRef: MatDialogRef<ProvidersPageComponent>,
    @Inject(MAT_DIALOG_DATA)
    public data: createProviderType,
    private formBuilder: FormBuilder
  ) {}

  createProviderForm = this.formBuilder.group({
    providerName: new FormControl('', [Validators.required]),
    ratePerWatt: new FormControl('', [Validators.required, Validators.pattern(/[0-9]/)])
  }) 

  providerNameControl() {
    return this.createProviderForm.get('providerName');
  }

  cancelDialog(): void {
    this.dialogRef.close();
  }
}
