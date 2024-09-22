import { CommonModule, JsonPipe } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import {  AbstractControl, FormBuilder,  FormGroup, FormsModule, ReactiveFormsModule, ValidationErrors, Validator, ValidatorFn, Validators } from '@angular/forms';


@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule,JsonPipe,ReactiveFormsModule,CommonModule,HttpClientModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {



  isfocus:boolean=false
  userDetails:any ={}
  district:{[key:string]: string[]} = {}

  



  registerForm: FormGroup;

  constructor(private formBuilder: FormBuilder, http:HttpClient) {
    this.registerForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      city: ['', [Validators.required]],
      password: ['', [Validators.required, Validators.minLength(8)]],
      retype_password: ['',[Validators.required, Validators.minLength(8)]]
    },
    {validators: this.passwordmatchValidator()},
  );

  debugger
  this.getListofLocationObj();
  }


  passwordmatchValidator(): ValidatorFn{

    return (control: AbstractControl): ValidationErrors | null => {
      
      const password = control.get('password')
      const retypePassword = control.get('retype_password');

      if (password && retypePassword && password.value !== retypePassword.value) {
        return { passwordsNotMatch: true };
      }
      return null;
    };

  }


  formvalue:any;

// return distict name  
  districStr:string=''
  
  getListofLocationObj(){
    this.http.get("http://localhost:3000/getListOfDistrict").subscribe((res:any) =>{
      this.district = res
    }, err =>{
      console.log("fail to feech api")
    }
  )



  }
  

// Returns the list of cities based on the selected state (city)
  getListedCity(){
    if(this.districStr){
      const selectedCities: string[] =[];
      for(const district in this.district){
        if(district === this.districStr){
          selectedCities.push(...this.district[district])
        }
      }
      return selectedCities;
    }
    return [];
  }

  // return list of district 
  getDistrictKey(): string[]{
    return Object.keys(this.district)
  }


  http=inject(HttpClient)

onSave(){
  const formValues = this.registerForm.value;
    this.userDetails={
            "email": formValues.email,
            "district": this.districStr,
            "city": formValues.city,
            "password": formValues.password
    }

    this.http.post("http://localhost:3000/register",this.userDetails).subscribe((res:any)=>{
      alert("Account Register Sucessful")

    },
    (error) =>{
      if(error.status == 400){
        alert("Validation failed. Please check your input.");
      }
      else if(error.status == 401){
        alert("User name alread existe.");
      }


    }
  )
}

  

}
