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


    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public boolean register(Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);
        return true;
    }

    public String verify(Users user) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            String token=jwtService.generateToken(user.getEmail());
            tokenDTo.setCheckToken(token);
            return token;
        } else {
            return "fail";
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