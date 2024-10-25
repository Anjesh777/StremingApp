import { Component, inject, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { HeaderserviceService } from '../../service/headerservice.service';

@Component({
  selector: 'app-account',
  standalone: true,
  imports: [],
  templateUrl: './account.component.html',
  styleUrl: './account.component.css'
})
export class AccountComponent{
  headerSevice=inject(HeaderserviceService)

  ngOnInit(): void {
    this.headerSevice.sendHeader()
  }







}
