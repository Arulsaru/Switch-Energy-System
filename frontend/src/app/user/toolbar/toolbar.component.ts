import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { SmartMeterService } from 'src/app/service/smart-meter.service';

@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.css'],
})
export class ToolbarComponent {
  userName: String = 'Arulsaru';
  selectedProvider: String = '';
  constructor(private router: Router) {}

  redirectToSmartMeterPage(): void {
    this.router.navigate(['/admin/smart-meter']);
  }
}
