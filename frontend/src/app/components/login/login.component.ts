import { HttpClient, HttpClientModule, HttpErrorResponse } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import {  Router } from '@angular/router';


@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule,ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  loginForm:FormGroup;
  registerValue:any;
  userDetails:any ={}
  isLogin:boolean=false;
  isError:boolean=false;
  Message:String=''

  http = inject(HttpClient)
  router = inject(Router)

  constructor(private formBuilder: FormBuilder) {
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]]
    });
  }
  

  onLogin() {
    const formValues = this.loginForm.value;
    this.userDetails = {
      "email": formValues.email,
      "password": formValues.password
    };
    
  
    this.http.post("http://localhost:3000/login", this.userDetails,{responseType: 'text' }).subscribe(
      (res: any) => {
        this.isLogin = true;
        this.isError = false;
        localStorage.setItem('token', res);
        this.Message = res;
        this.router.navigateByUrl('/home')
      },
      (error: HttpErrorResponse) => {
        this.isLogin = false;
        this.isError = true;
        console.log(error)
        if (error.status === 400 && error.error) {

          this.Message = error.error; 
        } else {
          this.Message = "Invalid user name or password";
        }
        
      }
    );
  }
  
  
  
  
  




}
