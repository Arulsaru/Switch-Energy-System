import { Component } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css'],
})
export class LoginPageComponent {
  showPassword: boolean = false;

  togglePasswordVisibility(): void {
    this.showPassword = !this.showPassword;
  }

  constructor(private formBuilder: FormBuilder) {}

  loginForm = this.formBuilder.group({
    userName: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required])
  })
}
