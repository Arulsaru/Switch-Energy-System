import { Component } from '@angular/core';
import { AbstractControl, FormBuilder, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { UserService } from '../../service/user.service';

@Component({
  selector: 'app-signup-page',
  templateUrl: './signup-page.component.html',
  styleUrls: ['./signup-page.component.css'],
})
export class SignupPageComponent {
  showPassword: boolean = false;
  showPasswordForReEnterPasswordField: boolean = false;
  isUserNameAlreadyAvailable: boolean = false;
  constructor(private formBuilder: FormBuilder, private service: UserService) {}

  togglePasswordVisibility(): void {
    this.showPassword = !this.showPassword;
  }

  togglePasswordVisibilityForReEnterPasswordField(): void {
    this.showPasswordForReEnterPasswordField =
      !this.showPasswordForReEnterPasswordField;
  }

  signUpForm = this.formBuilder.group({
    userName: [
      '',
      [Validators.required, Validators.pattern(/[A-Za-z]/)],
    ],
    password: ['', [Validators.required]],
    reEnterPassword: ['Arulsaru', [Validators.required]], 
    email: ['saruarul154@gmail.com', [Validators.required]],
    phoneNumber: ['1234567890', Validators.required],
  });

  get userName(): AbstractControl {
    return this.signUpForm.get('userName')!;
  }

  get password(): AbstractControl {
    return this.signUpForm.get('password')!;
  }

  get reEnterPassword(): AbstractControl {
    return this.signUpForm.get('reEnterPassword')!;
  }

  get email(): AbstractControl {
    return this.signUpForm.get('email')!;
  }

  get phoneNumber(): AbstractControl {
    return this.signUpForm.get('phoneNumber')!;
  }

  createUser(): void {
    console.log(this.signUpForm.value);
    this.service.createUser(this.signUpForm.value).subscribe({
      error: () => {
        this.isUserNameAlreadyAvailable = true;
        setTimeout(() => {
        this.isUserNameAlreadyAvailable = false;
        }, 3000);
      }
    });
  }
}
