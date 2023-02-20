import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.css'],
})
export class ToolbarComponent {
  userName: string | null = sessionStorage.getItem('name');
  name = this.userName?.slice(1, this.userName.length - 1);

  selectedProvider: String = '';
  constructor(private route: Router) {}

  logout(): void {
    sessionStorage.clear();
    this.route.navigate(['/auth/login']);
  }
}
