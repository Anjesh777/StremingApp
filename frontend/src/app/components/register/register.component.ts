import { CommonModule, JsonPipe } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import {  AbstractControl, FormBuilder,  FormGroup, FormsModule, ReactiveFormsModule, ValidationErrors, Validator, ValidatorFn, Validators } from '@angular/forms';
import {  Router } from '@angular/router';


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
  formvalue:any;
  // return distict name  
  districStr:string=''
  isRegister:boolean=false
  isError: boolean= false

 

  



  registerForm: FormGroup;

  constructor(private formBuilder: FormBuilder, http:HttpClient,private router: Router) {
    this.registerForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      city: ['', [Validators.required]],
      password: ['', [Validators.required, Validators.minLength(8)]],
      retype_password: ['',[Validators.required, Validators.minLength(8)]]
    },
    {validators: this.passwordmatchValidator()},
  );

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



  getListofLocationObj(){
    this.http.get("http://localhost:3000/register/getListOfDistrict").subscribe((res:any) =>{
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

      this.isRegister=true
    },
    (error) =>{
      console.log(error)
      this.isError=true
    }
  )
}

onSuccess(){

  this.isRegister=false
  this.router.navigate(['/verification'])


}



  

}
