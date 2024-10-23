import { HttpClient } from '@angular/common/http';
import { inject, Inject, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class HeaderserviceService {

  constructor() { }
  http = inject(HttpClient)

  sendHeader() {
    
    const headers = localStorage.getItem('token')
    this.http.post("http://localhost:3000/header",{headers})
    .subscribe(response => {
      console.log("Success", response);
    }, error => {
      console.error("Error", error);
    });

  }
  
}
