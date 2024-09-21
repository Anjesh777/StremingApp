import { CommonModule, JsonPipe } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder,  FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule,JsonPipe,ReactiveFormsModule,CommonModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {

  registerForm: FormGroup;

  constructor(private formBuilder: FormBuilder) {
    this.registerForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      city: ['', [Validators.required]],
      password: ['', [Validators.required, Validators.minLength(8)]],
      retype_password: ['',[Validators.required, Validators.minLength(8)]]


    });
  }

  getpassword(){
    return this.registerForm.controls['password']
  }
  getretype_password(){
    return this.registerForm.controls['retype_password']
  }
  
  


  onSave() {
    
    

  }
  

  district:{[key:string]: string[]} = {

    Bhojpur: ["Bhojpur Municipality", "Shadananda"],
    Dhankuta: ["Dhankuta Municipality", "Pakhribas"],
    Ilam: ["Ilam Municipality", "Mai"],
    Jhapa: ["Bhadrapur", "Damak", "Mechinagar","Birtamode"],
    Khotang: ["Diktel", "Halesi"],
    Morang: ["Biratnagar", "Belbari"],
    Okhaldhunga: ["Siddhicharan", "Molung"],
    Panchthar: ["Phidim"],
    Sankhuwasabha: ["Khandbari", "Chainpur"],
    Solukhumbu: ["Salleri", "Namche Bazaar"],
    Sunsari: ["Inaruwa", "Itahari", "Dharan"],
    Taplejung: ["Phungling", "Mikwakhola"],
    Terhathum: ["Myanglung"],
    Udayapur: ["Triyuga", "Katari"],

    Bara: ["Kalaiya", "Nijgadh"],
    Dhanusha: ["Janakpur", "Dhanushadham"],
    Mahottari: ["Jaleshwor", "Gaushala"],
    Parsa: ["Birgunj"],
    Rautahat: ["Gaur", "Chandranigahapur"],
    Saptari: ["Rajbiraj"],
    Sarlahi: ["Malangwa"],
    Siraha: ["Siraha", "Lahan"],

    Bhaktapur: ["Bhaktapur", "Thimi"],
    Chitwan: ["Bharatpur", "Ratnanagar"],
    Dhading: ["Dhadingbesi"],
    Dolakha: ["Charikot"],
    Kathmandu: ["Kathmandu", "Kirtipur", "Budhanilkantha"],
    Kavrepalanchok: ["Dhulikhel", "Panauti"],
    Lalitpur: ["Lalitpur", "Godavari"],
    Makwanpur: ["Hetauda"],
    Nuwakot: ["Bidur"],
    Ramechhap: ["Manthali"],
    Rasuwa: ["Dhunche"],
    Sindhuli: ["Kamalamai"],
    Sindhupalchok: ["Chautara"],

    Baglung: ["Baglung Municipality", "Galkot"],
    Gorkha: ["Gorkha Municipality"],
    Kaski: ["Pokhara", "Lekhnath"],
    Lamjung: ["Besisahar"],
    Manang: ["Chame"],
    Mustang: ["Jomsom"],
    Myagdi: ["Beni"],
    Nawalpur: ["Kawasoti"],
    Parbat: ["Kushma"],
    Syangja: ["Putalibazar"],
    Tanahun: ["Damauli"],

    Arghakhanchi: ["Sandhikharka"],
    Banke: ["Nepalgunj"],
    Bardiya: ["Gulariya"],
    Dang: ["Ghorahi", "Tulsipur"],
    Gulmi: ["Tamghas"],
    Kapilvastu: ["Taulihawa"],
    Parasi: ["Parasi"],
    Palpa: ["Tansen"],
    Pyuthan: ["Pyuthan"],
    Rolpa: ["Liwang"],
    "Rukum (East)": ["Rukumkot"],
    Rupandehi: ["Butwal", "Bhairahawa"],

    Dailekh: ["Narayan"],
    Dolpa: ["Dunai"],
    Humla: ["Simikot"],
    Jajarkot: ["Khalanga"],
    Jumla: ["Khalanga"],
    Kalikot: ["Manma"],
    Mugu: ["Gamgadhi"],
    "Rukum (West)": ["Musikot"],
    Salyan: ["Salyan"],
    Surkhet: ["Birendranagar"],

    Achham: ["Mangalsen"],
    Baitadi: ["Dasharathchand"],
    Bajhang: ["Chainpur"],
    Bajura: ["Martadi"],
    Dadeldhura: ["Amargadhi"],
    Darchula: ["Darchula"],
    Doti: ["Dipayal"],
    Kailali: ["Dhangadhi"],
    Kanchanpur: ["Mahendranagar"],
  }

  formvalue:any;
  city:string=''
  
  


  // Returns the list of cities based on the selected state (city)
  getListedCity(){
    if(this.city){
      const selectedCities: string[] =[];
      for(const district in this.district){
        if(district === this.city){
          selectedCities.push(...this.district[district])
        }
      }
      return selectedCities;
    }
    return [];
  }

  getDistrictKey(): string[]{
    return Object.keys(this.district)
  }
  

  

  



}
