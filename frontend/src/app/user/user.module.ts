import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserComponent } from './user.component';
import { ToolbarComponent } from './toolbar/toolbar.component';
import { MaterialModule } from 'src/material/material.module';
import { RouterModule } from '@angular/router';
import { UserSmartMeterComponent } from './user-smart-meter/user-smart-meter.component';

@NgModule({
  declarations: [UserComponent, ToolbarComponent, UserSmartMeterComponent],
  imports: [CommonModule, MaterialModule, RouterModule],
})
export class UserModule {}
