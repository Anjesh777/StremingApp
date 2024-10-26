package com.example.videoServer.dto;


import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Configuration
@Getter
@Setter

public class TokenDTo {


    private String token;
    private String checkToken;
    private boolean validity;

    public boolean checkToken(){
        return token.equals("Bearer "+checkToken) || (checkToken == null || checkToken.isEmpty());
    }


}
