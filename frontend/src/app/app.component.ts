import { Component, inject, Inject } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { HeaderserviceService } from './service/headerservice.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet,RouterLink],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'frontend';

  isLogin:boolean=false
  toggleDetails:boolean=false

  headerService = inject(HeaderserviceService)

  

  ngOnInit(): void {
    this.headerService.homeHide();
  }

 
  


}
