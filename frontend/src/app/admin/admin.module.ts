import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminComponent } from './admin.component';
import { AdminRoutingModule } from './admin-routing.module';
import { RouterModule } from '@angular/router';
import { AdminSmartMeterComponent } from './admin-smart-meter/admin-smart-meter.component';
import { CreateProviderDialogComponent } from './create-provider-dialog/create-provider-dialog.component';
import { ProvidersPageComponent } from './providers-page/providers-page.component';
import { ToolbarComponent } from './toolbar/toolbar.component';
import { MaterialModule } from 'src/app/shared/material/material.module';

@NgModule({
  declarations: [
    AdminComponent,
    AdminSmartMeterComponent,
    CreateProviderDialogComponent,
    ProvidersPageComponent,
    ToolbarComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    AdminRoutingModule,
    MaterialModule
  ]
})
export class AdminModule { }
