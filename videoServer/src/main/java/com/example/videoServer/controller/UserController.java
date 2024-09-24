package com.example.videoServer.controller;

import com.example.videoServer.model.Users;
import com.example.videoServer.service.SendEmailService;
import com.example.videoServer.service.UserService;
import com.example.videoServer.service.utility.UtilityClass1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private SendEmailService sendEmailService;

    @Autowired
    private UtilityClass1 utilityClass1;

    private int otpSystem;

    @CrossOrigin("http://localhost:4200")
    @GetMapping("register/getListOfDistrict")
    public Map<String, List<String>> getDistricts(){

        Map<String,List<String>> district= new HashMap<>();

        district.put("Bhojpur", List.of("Bhojpur Municipality", "Shadananda"));
        district.put("Dhankuta", List.of("Dhankuta Municipality", "Pakhribas"));
        district.put("Ilam", List.of("Ilam Municipality", "Mai"));
        district.put("Jhapa", List.of("Bhadrapur", "Damak", "Mechinagar", "Birtamode"));
        district.put("Khotang", List.of("Diktel", "Halesi"));
        district.put("Morang", List.of("Biratnagar", "Belbari"));
        district.put("Okhaldhunga", List.of("Siddhicharan", "Molung"));
        district.put("Panchthar", List.of("Phidim"));
        district.put("Sankhuwasabha", List.of("Khandbari", "Chainpur"));
        district.put("Solukhumbu", List.of("Salleri", "Namche Bazaar"));
        district.put("Sunsari", List.of("Inaruwa", "Itahari", "Dharan"));
        district.put("Taplejung", List.of("Phungling", "Mikwakhola"));
        district.put("Terhathum", List.of("Myanglung"));
        district.put("Udayapur", List.of("Triyuga", "Katari"));

        return district;

    }

    @CrossOrigin("http://localhost:4200")
    @PostMapping("register")
    public ResponseEntity<Users> register(@RequestBody Users user) {
        otpSystem= utilityClass1.generateRandomNumber();
        System.out.println(otpSystem);
       if (service.register(user)){
           sendEmailService.sendEmail(user.getEmail(), "System Generated OTP: " + otpSystem, "otp");
           return new ResponseEntity<>(HttpStatus.CREATED);
       }
       else {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
    }


    @CrossOrigin("http://localhost:4200")
    @GetMapping("register/otpVerification")
    public ResponseEntity<String> otpVerification(@RequestParam("otpcode") int otpcode) {
        if (otpcode == otpSystem) {
            System.out.println("OTP successful");
            return new ResponseEntity<>("OTP verification successful", HttpStatus.OK);
        } else {
            System.out.println("OTP failed");
            return new ResponseEntity<>("OTP verification failed", HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("login")
    public String login(@RequestBody Users user) {
        return service.verify(user);
    }


    @GetMapping("/all")
    public List<Users> gellAll(){
        return service.getall();
    }


}