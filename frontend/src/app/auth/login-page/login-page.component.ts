import { Component } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/shared/service/user.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css'],
})
export class LoginPageComponent {
  showPassword: boolean = false;
  isUserNameNotAvailable: boolean = false;

  togglePasswordVisibility(): void {
    this.showPassword = !this.showPassword;
  }

  constructor(private formBuilder: FormBuilder, private userService: UserService, private router: Router) {}

  loginForm = this.formBuilder.group({
    userName: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required])
  })

  get userName(): AbstractControl {
    return this.loginForm.get('userName')!;
  }

  get password(): AbstractControl {
    return this.loginForm.get('password')!;
  }

  login(): void {
    let token: string = '';
    this.userService.generateToken(this.loginForm.value).subscribe({
      next: (response) => { 
        token = JSON.stringify(response);
        token = token.slice(13, token.length - 2);
        sessionStorage.setItem('token', token);
        this.storeUserRoleInSessionStorage();
        this.storeUserNameInSessionStorage();
      },
      error: (err) => {
        console.log(err);
        this.isUserNameNotAvailable = true;
        this.loginForm.reset();
        setTimeout(() => {
          this.isUserNameNotAvailable = false;
        }, 3000);
      }
    })
  }

  storeUserRoleInSessionStorage(): void {
    this.userService.getUserByName(this.loginForm.value.userName).subscribe({
      next: (response) => {
        sessionStorage.setItem('role', JSON.stringify(response.role));
        this.naviagetePageWithRespectToRoles(response.role);  // idhu maathanuuuu 
      } 
    })
  }

  storeUserNameInSessionStorage(): void {
    sessionStorage.setItem('name', JSON.stringify(this.loginForm.value.userName));
  }

  naviagetePageWithRespectToRoles(role: String) {
    if(role === 'ADMIN') {
      this.router.navigate(['/admin']);
    } else {
      this.router.navigate(['/user']);
    }
  }
  
 }
