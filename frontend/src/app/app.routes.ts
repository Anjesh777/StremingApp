import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { OtpComponent } from './components/otp/otp.component';

export const routes: Routes = [
    {
        path:"login",
        component:LoginComponent
    },
    {
        path:"register",
        component:RegisterComponent
    },
    {
        path:"verification",
        component:OtpComponent
    }


];
