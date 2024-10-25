import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, map, Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HeaderserviceService {




  jsonData:any;
  http = inject(HttpClient)
  router = inject(Router)

  sendHeader(): Observable<boolean> {
    const headers = localStorage.getItem('token');
    
    return this.http.post("http://localhost:3000/header", { headers })
      .pipe(
        map(response => {
          console.log("Success", response);
          return true;  
        }),
        catchError(error => {
          console.error("Error", error);
          return of(false);  
        })
      );
  }

  isHomeVisible = false;

  homeHide(): void {
    this.sendHeader().subscribe((isvalidate: boolean) => {
      if (isvalidate) {
        this.isHomeVisible = true;  
      } else {
        this.isHomeVisible = false;
      }
    });
  }

  routeRegister(url:string){
    this.router.navigateByUrl(`/${url}`)
   }

   clearToken(){
    localStorage.clear();
  }

  getuserDetails(){

    const email = sessionStorage.getItem('useremail')
    this.http.post("http://localhost:3000/details",email,{responseType:'json'}).subscribe(
      (res: any) =>{
        this.jsonData=res
        console.log(this.jsonData)
      },
      (error:HttpErrorResponse) =>{
        console.log(error.error)
      }
      
    )
    




    
  }
  
}
