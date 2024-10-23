import { CommonModule, JsonPipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import {  Router } from '@angular/router';


@Component({
  selector: 'app-otp',
  standalone: true,
  imports: [FormsModule,JsonPipe,ReactiveFormsModule,CommonModule],
  templateUrl: './otp.component.html',
  styleUrl: './otp.component.css'
})
export class OtpComponent {

 
  mergeNumber: string = ''
  userDetails: any
  isValidator: boolean =false
  isError: boolean = false
  Message: string = ''

  optValidor:FormGroup;
  constructor(private formBuilder: FormBuilder, private router:Router){

    this.userDetails = router.getCurrentNavigation()?.extras.state?.['userDetails'];

    this.optValidor = this.formBuilder.group({
    f1: ['', [Validators.required, Validators.pattern(/^-?\d+(\.\d+)?$/)]],
    f2: ['', [Validators.required, Validators.pattern(/^-?\d+(\.\d+)?$/)]],
    f3: ['', [Validators.required, Validators.pattern(/^-?\d+(\.\d+)?$/)]],
    f4: ['', [Validators.required, Validators.pattern(/^-?\d+(\.\d+)?$/)]],
    })


  }

http = inject(HttpClient)


submitOTP() {
  if (this.optValidor.valid) {
    // Combine the OTP values
    this.mergeNumber = this.optValidor.value.f1 + this.optValidor.value.f2 + this.optValidor.value.f3 + this.optValidor.value.f4;
    console.log(this.mergeNumber);

    // Make the HTTP request
    this.http.get(`http://localhost:3000/register/otpVerification?otpcode=${this.mergeNumber}`, { responseType: 'text' })
      .subscribe((response: any) => {
        // Success response
        this.isValidator = true;
        this.Message = response;  // Set the success message

      }, error => {
        // Error response
        this.isError = true;
        this.Message = error.error;  
        console.error("Error occurred:", error);  
      });

  } else {
    console.error("Form is invalid");
  }
}

navigatetoHome(){
  this.isValidator=false
  this.router.navigate(['/login'])
}



}