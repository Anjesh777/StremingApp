import { CommonModule, JsonPipe } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute , Router } from '@angular/router';

@Component({
  selector: 'app-otp',
  standalone: true,
  imports: [FormsModule,JsonPipe,ReactiveFormsModule,CommonModule,HttpClientModule],
  templateUrl: './otp.component.html',
  styleUrl: './otp.component.css'
})
export class OtpComponent {

 
  mergeNumber: string = ''
  userDetails: any



  optValidor:FormGroup;
  constructor(private formBuilder: FormBuilder, http:HttpClient, router: Router,route:ActivatedRoute){

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
    this.mergeNumber = this.optValidor.value.f1 + this.optValidor.value.f2 + this.optValidor.value.f3 + this.optValidor.value.f4;
    console.log(this.mergeNumber);

    this.http.get(`http://localhost:3000/register/otpVerification?otpcode=${this.mergeNumber}`)
      .subscribe((res: any) => {
        console.log(res);  
      }, error => {
        console.error("Error occurred:", error);  // Handle error
      });
  } else {
    // Handle invalid form
    console.error("Form is invalid");
  }

}
}