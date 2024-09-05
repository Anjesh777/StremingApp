package com.example.videoServer.model.video;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="yt_cources")
public class Cources {

    @Id
    private String id;
    private String title;

}
