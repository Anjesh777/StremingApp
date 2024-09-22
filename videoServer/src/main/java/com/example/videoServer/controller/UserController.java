package com.example.videoServer.controller;

import com.example.videoServer.model.Users;
import com.example.videoServer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @CrossOrigin("http://localhost:4200")
    @GetMapping("getListOfDistrict")
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
    @PostMapping("/register")
    public Users register(@RequestBody Users user) {
        return service.register(user);
    }


    @PostMapping("/login")
    public String login(@RequestBody Users user) {
        return service.verify(user);
    }


    @GetMapping("/all")
    public List<Users> gellAll(){
        return service.getall();
    }


}