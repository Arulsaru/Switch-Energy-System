import { Component } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { UserService } from 'src/app/service/user.service';

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

  constructor(private formBuilder: FormBuilder, private userService: UserService) {}

  loginForm = this.formBuilder.group({
    userName: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required])
  })

  login(): void {
    console.log("Arul")
    let token: string = '';
    this.userService.generateToken(this.loginForm.value).subscribe({
      next: (response) => { 
        token = JSON.stringify(response);
        token = token.slice(13, token.length - 2);
        console.log(token);
        sessionStorage.setItem("token", token);
      }
    })
  }
}
