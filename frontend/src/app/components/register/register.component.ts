import { CommonModule, JsonPipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule, JsonPipe, ReactiveFormsModule, CommonModule],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  isfocus: boolean = false;
  userDetails: any = {};
  district: { [key: string]: string[] } = {};
  formvalue: any;
  districStr: string = '';
  isRegister: boolean = false;
  isError: boolean = false;
  Message: String = '';

  registerForm: FormGroup;
  http=inject(HttpClient)

  constructor(private formBuilder: FormBuilder, private router: Router) {
    this.registerForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      city: ['', [Validators.required]],
      password: ['', [Validators.required, Validators.minLength(8)]],
      retype_password: ['', [Validators.required, Validators.minLength(8)]]
    },
    { validators: this.passwordmatchValidator() });

    this.getListofLocationObj();
  }

  passwordmatchValidator(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      const password = control.get('password');
      const retypePassword = control.get('retype_password');

      if (password && retypePassword && password.value !== retypePassword.value) {
        return { passwordsNotMatch: true };
      }
      return null;
    };
  }

  getListofLocationObj() {
    this.http.get("http://localhost:3000/register/getListOfDistrict").subscribe((res: any) => {
      this.district = res;
    }, err => {
      this.Message = err.error;
      console.log("fail to fetch API " + err);
    });
  }

  getListedCity() {
    if (this.districStr) {
      const selectedCities: string[] = [];
      for (const district in this.district) {
        if (district === this.districStr) {
          selectedCities.push(...this.district[district]);
        }
      }
      return selectedCities;
    }
    return [];
  }

  getDistrictKey(): string[] {
    return Object.keys(this.district);
  }

  onSave() {
    const formValues = this.registerForm.value;
    this.userDetails = {
      "email": formValues.email,
      "district": this.districStr,
      "city": formValues.city,
      "password": formValues.password
    };

    this.http.post("http://localhost:3000/register", this.userDetails).subscribe((res: any) => {
      this.isRegister = true;
    },
    (error) => {
      console.log(error);
      this.isError = true;
    });
  }

  onSuccess() {
    this.isRegister = false;
    this.router.navigate(['/verification']);
  }
}
