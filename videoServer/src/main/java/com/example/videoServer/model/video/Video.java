package com.example.videoServer.model.video;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "yt_video")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Video {

    @Id
    private String videoId;
    private String title;
    private String description;
    private String contentType;
    private String filePath;

}
