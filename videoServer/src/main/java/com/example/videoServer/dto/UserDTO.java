package com.example.videoServer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter

public class UserDTO {

    private int id;
    private String city;
    private String district;
    private String email;
    private String isVerified;
    private String userName;

}
