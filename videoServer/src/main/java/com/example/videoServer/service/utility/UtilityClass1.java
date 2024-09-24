package com.example.videoServer.service.utility;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UtilityClass1 {

    public int generateRandomNumber(){

        Random random = new Random();
        int min=1000;
        int max = 10000;
        return random.nextInt((max-min)+1)+min;
    }

}
