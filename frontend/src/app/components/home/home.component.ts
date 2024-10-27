import { HttpClient} from '@angular/common/http';
import { Component, inject } from '@angular/core';
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
  router=inject(Router)
   headerService = inject(HeaderserviceService)


  
  
  ngOnInit(): void{
    this.headerService.sendHeader()
    
      .subscribe((isvalidate:boolean) =>{
        if(isvalidate){
          this.headerService.getuserDetails()
          //this.router.navigateByUrl('/home')
          
        }
        else{
          this.router.navigateByUrl('/login')
        }
      }
    )
    this.headerService.homeHide();
  }


   getAll() {
    
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
