import { Component } from '@angular/core';

@Component({
    selector: 'app-signup-page',
    templateUrl: './signup-page.component.html',
    styleUrls: ['./signup-page.component.css']
})

export class SignupPageComponent {
    showPassword: boolean = false;
    showPasswordForReEnterPasswordField: boolean = false;

    togglePasswordVisibility(): void {
      this.showPassword = !this.showPassword;
    }

    togglePasswordVisibilityForReEnterPasswordField(): void {
      this.showPasswordForReEnterPasswordField = !this.showPasswordForReEnterPasswordField;
    }
}