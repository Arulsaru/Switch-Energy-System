import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
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
      'Arulmozhi',
      [Validators.required, Validators.pattern(/[A-Za-z]/)],
    ],
    password: ['Arulsaru', [Validators.required]],
    reEnterPassword: ['Arulsaru', [Validators.required]],
    email: ['saruarul154@gmail.com', [Validators.required]],
    phoneNumber: ['1234567890', Validators.required],
  });

  print(): void {
    console.log(this.signUpForm.value);
    this.service.createUser(this.signUpForm.value).subscribe(
      (res) => {},
      (err) => {
        this.isUserNameAlreadyAvailable = true;
        setTimeout(() => {
          this.isUserNameAlreadyAvailable = false;
        }, 3000);
      }
    );
  }
}
