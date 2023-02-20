import { Component, OnInit } from '@angular/core';
import { createProviderType } from '../../shared/interface/provider';
import { providerType } from '../../shared/interface/provider';
import { ProviderService } from '../../shared/service/provider.service';
import Swal from 'sweetalert2';
import { MatDialog } from '@angular/material/dialog';
import { CreateProviderDialogComponent } from '../create-provider-dialog/create-provider-dialog.component';

@Component({
  selector: 'app-providers-page',
  templateUrl: './providers-page.component.html',
  styleUrls: ['./providers-page.component.css'],
})

export class ProvidersPageComponent implements OnInit {
  providers: providerType[] = [];

  newProviderName: String = '';
  newProviderRatePerWatt: Number = 0;
  newProvider: createProviderType = {
    providerName: '',
    ratePerWatt: 0,
  };
  isLastProviderEnabled: boolean = false;
  createProviderCancelButtonClicked: boolean = false;

  constructor(private service: ProviderService, public dialog: MatDialog) {}

  ngOnInit() {
    this.getAllProviders();
  }

  getAllProviders(): void {
    this.service.getAllProviders().subscribe({
      next: (response) => {
        this.providers = response;
      }
    });
  }

  createProvider(): void {
    const dialogRef = this.dialog.open(CreateProviderDialogComponent, {
      data: {
        newProvider: this.newProvider,
      },
    });
    dialogRef.afterClosed().subscribe({
      next: (response) => {
        this.newProvider = response;
        this.service.createProviders(this.newProvider).subscribe({
          next: () => this.getAllProviders(),
          error: () => {
            console.log("asfd");
            this.createProviderCancelButtonClicked = true;
            window.setTimeout(() => {
              this.createProviderCancelButtonClicked = false;
            }, 4000);
          }
        })
      },
      
    });
  }

  showUsersList(provider: providerType): void {
    this.getAllProviders();
    let message: String = '';

    if (provider.smartMetersList.length == 0) {
      message = 'No smartmeter is using this provider';
    } else if (provider.smartMetersList.length < 5) {
      message = provider.smartMetersList.toString();
    } else {
      message = `${provider.smartMetersList.splice(0, 5).toString()} and ${provider.smartMetersList.length - 5} moree..`;
    }

    Swal.fire({
      title: provider.providerName,
      text: message.toString(),
      // footer: '<a href="">Why do I have this issue?</a>'  // redirect to a new page while users list is large
    });
  }

  enableOrDisableProvider(provider: providerType): void {
    if (provider.enabled) { // disable Provider
      Swal.fire({
        title: 'Are you sure?',
        text: `You want to disable ${provider.providerName} provider.. 
        This will switch all the smartmeter using this provider to another enabled provider`,
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, Disable it!',
      }).then((result) => {
        if (result.isConfirmed) {
          this.service.disableProvider(provider.providerName).subscribe({
            next: () =>
              Swal.fire(
                'Disabled!',
                `Provider ${provider.providerName} is disabled`,
                'success'
              ).then((result) => {
                if (result.isConfirmed) {
                  this.getAllProviders();
                }
              }),
            error: () => {
              this.isLastProviderEnabled = true;
              setTimeout(() => {
                this.isLastProviderEnabled = false;
              }, 3000);
            },
          });
        }
      });
    } else {
      Swal.fire({
        title: 'Are you sure?',
        text: `You want to enable ${provider.providerName} provider`,
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, Enable it!',
      }).then((result) => {
        if (result.isConfirmed) {
          this.service.enableProvider(provider.providerName).subscribe({
            next: () => Swal.fire(
              'Enabled!',
              `provider ${provider.providerName} is enabled`,
              'success'
            ).then((result) => {
              if (result.isConfirmed) {
                this.getAllProviders();
              }
            })
          });
        }
      });
    }
  }
}
