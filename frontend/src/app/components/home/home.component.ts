import { HttpClient, HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import { Component, inject, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { HeaderserviceService } from '../../service/headerservice.service';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [RouterModule], // Import HttpClientModule and RouterModule
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent  {
  homeList: any=[];
  http = inject(HttpClient)
  private headerService = inject(HeaderserviceService)



  ngOnInit(): void{

    this.headerService.sendHeader()

  }
    



  

  

   getAll() {
     const token = localStorage.getItem('token');
     this.http.get("http://localhost:3000/getAll").subscribe(
       (res: any) => {
         console.log("Api work")
       },
       (err) => {
         console.log(err);
       }
     );
   }

  

  
}  
