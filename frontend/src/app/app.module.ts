import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from 'src/material/material.module';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { SignupPageComponent } from './signup-page/signup-page.component';
import { DescriptionComponent } from './description/description.component';
import { MainPageComponent } from './main-page/main-page.component';
import { ToolbarComponent } from './toolbar/toolbar.component';
import { ProvidersPageComponent } from './providers-page/providers-page.component';
import { AdminSmartMeterComponent } from './admin-smart-meter/admin-smart-meter.component';
import { UserSmartMeterComponent } from './user-smart-meter/user-smart-meter.component';
import { CreateProviderDialogComponent } from './create-provider-dialog/create-provider-dialog.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginPageComponent,
    SignupPageComponent,
    DescriptionComponent,
    MainPageComponent,
    ToolbarComponent,
    ProvidersPageComponent,
    AdminSmartMeterComponent,
    UserSmartMeterComponent,
    CreateProviderDialogComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }