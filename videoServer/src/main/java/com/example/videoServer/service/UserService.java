package com.example.videoServer.service;

import com.example.videoServer.dto.TokenDTo;
import com.example.videoServer.dto.UserDTO;
import com.example.videoServer.model.Users;
import com.example.videoServer.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private JWTService jwtService;
    @Autowired
    AuthenticationManager authManager;
    @Autowired
    private UserRepo repo;
    @Autowired
    private TokenDTo tokenDTo;
    private List<TokenDTo> userTokenList = new ArrayList<>();



    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public boolean register(Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);
        return true;
    }

    public String verify(Users user) {
        // Authenticate the user
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
        );

        if (authentication.isAuthenticated()) {
            String newToken = jwtService.generateToken(user.getEmail());
            // changed here
            tokenDTo.setCheckToken(newToken);

            boolean userExists = false;
            for (TokenDTo tokenDTo : userTokenList) {
                if (tokenDTo.getUser().getEmail().equals(user.getEmail())) {
                    tokenDTo.setCheckToken(newToken);  // Update token
                    userExists = true;
                    break;
                }
            }

            if (!userExists) {
                userTokenList.add(new TokenDTo(user, newToken));
            }

            printUserTokenList();

            return newToken;
        } else {
            return "fail";
        }
    }

    private void printUserTokenList() {
        for (TokenDTo tokenDTo : userTokenList) {
            System.out.println("User: " + tokenDTo.getUser().getEmail() + ", Token: " + tokenDTo.getToken());
        }
    }

    public UserDTO getUserDetails(String email){
        return repo.findByEmailExcludingPassword(email);
    }

//    public List<Users> getall(){
//        return repo.findAll();
//    }

    public void setVerifiedStatus(String email){
        Users user= repo.findByEmail(email);
        if (user != null){
            user.setIsVerified("true");
            repo.save(user);
        }

    }


}