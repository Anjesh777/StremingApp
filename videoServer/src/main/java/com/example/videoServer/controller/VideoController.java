package com.example.videoServer.controller;



import com.example.videoServer.model.video.Video;
import com.example.videoServer.service.videservice.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/videos")
public class VideoController {

    @Autowired
    VideoService videoService;

    @PostMapping
    public ResponseEntity<Video> create(
            @RequestParam("file")MultipartFile file,
            @RequestParam("title") String tittle,
            @RequestParam("description") String description
    ){

        Video video = new Video();
        video.setTitle(tittle);
        video.setDescription(description);
        video.setVideoId(UUID.randomUUID().toString());
        videoService.save(video,file);

        return null;


    }

}
