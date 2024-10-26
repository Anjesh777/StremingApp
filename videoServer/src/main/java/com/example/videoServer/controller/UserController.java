package com.example.videoServer.controller;

import com.example.videoServer.dto.UserDTO;
import com.example.videoServer.model.Users;
import com.example.videoServer.service.SendEmailService;
import com.example.videoServer.dto.TokenDTo;
import com.example.videoServer.service.UserService;
import com.example.videoServer.service.utility.UtilityClass1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService service;
    @Autowired
    private SendEmailService sendEmailService;
    @Autowired
    private UtilityClass1 utilityClass1;
    @Autowired
    private TokenDTo tokenService;

    private int otpSystem;
    private LocalDateTime otpGeneratedTime;
    Users usersObj= new Users();

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


    @PostMapping("register")
    public ResponseEntity<Users> register(@RequestBody Users user) {

        otpSystem= utilityClass1.generateRandomNumber();
        otpGeneratedTime = LocalDateTime.now();
       if (user !=null){
           usersObj.setEmail(user.getEmail());
           service.register(user);
           sendEmailService.sendEmail(user.getEmail(), "System Generated OTP: " + otpSystem, "otp");
           return new ResponseEntity<>(HttpStatus.CREATED);
       }
       else {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
    }
//
//    @GetMapping("register/otpVerification")
//    public ResponseEntity<String> otpVerification(@RequestParam("otpcode") int otpcode) {
//
//        if(otpGeneratedTime != null){
//            LocalDateTime currentTime = LocalDateTime.now();
//            long minpassed = Duration.between(otpGeneratedTime,currentTime).toMinutes();
//
//            if(minpassed >1){
//                otpSystem=0;
//                otpGeneratedTime=null;
//                return new ResponseEntity<>("OTP expired", HttpStatus.BAD_REQUEST);
//            }
//        }
//
//        if (otpcode == otpSystem) {
//            System.out.println("OTP successful");
//            service.setVerifiedStatus(usersObj.getEmail());
//            return new ResponseEntity<>("OTP verification successful", HttpStatus.OK);
//        } else {
//            System.out.println("OTP failed");
//            return new ResponseEntity<>("OTP verification failed", HttpStatus.BAD_REQUEST);
//        }
//    }
    @PostMapping("login")
    public String login(@RequestBody Users user) {
         return service.verify(user);
    }

    @PostMapping("/header")
    public boolean check(@RequestHeader("Authorization") String token){
        System.out.println("Received Token "+token);
        tokenService.setToken(token);
        return tokenService.checkToken();
    }

    @GetMapping("/getAll")
    public void all(){
        System.out.println("Api work");
    }

    @PostMapping("/details")
    public UserDTO users(@RequestBody String email){
        return service.getUserDetails(email);
    }




}