package com.example.videoServer.payload;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class CustomMessage {


    private String message;
    private boolean sucess = false;



}
