import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserComponent } from './user.component';
import { ToolbarComponent } from './toolbar/toolbar.component';
import { MaterialModule } from 'src/material/material.module';
import { RouterModule } from '@angular/router';
import { UserSmartMeterComponent } from './user-smart-meter/user-smart-meter.component';
import { UserRoutingModule } from './user-routing.module';
import { CreateSmartMeterDialogComponent } from './create-smart-meter-dialog/create-smart-meter-dialog.component';

@NgModule({
  declarations: [
    UserComponent,
    ToolbarComponent,
    UserSmartMeterComponent,
    CreateSmartMeterDialogComponent
  ],
  imports: [CommonModule, MaterialModule, RouterModule, UserRoutingModule],
})
export class UserModule {}
