package com.example.videoServer.dto;

import com.example.videoServer.model.Users;
import com.example.videoServer.service.JWTService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
@AllArgsConstructor

public class TokenDTo {



    private Users user;
    private String token;
    private String checkToken;
    private boolean validity;

    public TokenDTo() {}

    public TokenDTo(Users user, String token) {
        this.user = user;
        this.token = token;
    }

    public boolean checkToken() {
        // If checkToken is null, it means no token was provided to check
        System.out.println(token);

        System.out.println(checkToken);
        if (token==null ||  token.isEmpty() ) {
            return true;
        }
        if (checkToken.equals(token.substring(7))) {
            return true;
        }

        String formattedCheckToken = checkToken.startsWith("Bearer ") ? checkToken.substring(7) : checkToken;
        return token.equals(formattedCheckToken);






    }
}
