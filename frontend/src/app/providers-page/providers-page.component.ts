import { Component, OnInit } from '@angular/core';
import { providerType } from '../interface/providerType';
import { ProviderService } from '../service/provider.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-providers-page',
  templateUrl: './providers-page.component.html',
  styleUrls: ['./providers-page.component.css'],
})
export class ProvidersPageComponent implements OnInit {
  providers: providerType[] = [];

  constructor(private service: ProviderService) {}

  ngOnInit() {
    this.service.getAllProviders().subscribe((res: providerType[]) => {
      this.providers = res;
    });
  }

  showUsersList(provider: providerType): void {
    Swal.fire({
      title: provider.providerName,
      text: provider.usersList.toString(),
      // footer: '<a href="">Why do I have this issue?</a>'
    })
  }

  enableOrDisableProvider(provider: providerType): void {
    if (provider.enabled) { // disable Provider
      
      Swal.fire({
        title: 'Are you sure?',
        text: `You want to disable ${provider.providerName} provider.. 
        This will switch all the smartmeter using this provider to another provider`,
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, Disable it!'
      }).then((result) => {
        if (result.isConfirmed) {
          this.service.disableProvider(provider.providerName).subscribe();
          Swal.fire(
            'Disabled!',
            `Provider ${provider.providerName} is disabled`,
            'success'
          ).then((result) => {
            if(result.isConfirmed) {
              window.location.reload();
            }
          })
        }
      })
    } else {
      Swal.fire({
        title: 'Are you sure?',
        text: `You want to enable ${provider.providerName} provider`,
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, Enable it!'
      }).then((result) => {
        if (result.isConfirmed) {
          this.service.enableProvider(provider.providerName).subscribe();
          Swal.fire(
            'Enabled!',
            `provider ${provider.providerName} is enabled`,
            'success'
          ).then((result) => {
            if(result.isConfirmed) {
              window.location.reload();
            }
          })
        }
      })
    }
  }
}
