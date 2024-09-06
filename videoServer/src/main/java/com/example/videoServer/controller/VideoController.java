package com.example.videoServer.controller;



import com.example.videoServer.model.video.Video;
import com.example.videoServer.payload.CustomMessage;
import com.example.videoServer.service.videservice.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/videos")
public class VideoController {

    @Autowired
    VideoService videoService;

    @PostMapping
    public ResponseEntity<Object> create(
            @RequestParam("file")MultipartFile file,
            @RequestParam("title") String tittle,
            @RequestParam("description") String description
    ){

        Video video = new Video();
        video.setTitle(tittle);
        video.setDescription(description);
        video.setVideoId(UUID.randomUUID().toString());


        if (file == null || file.isEmpty()) {
            CustomMessage errorMessage = CustomMessage.builder()
                    .message("Video upload not successful")
                    .sucess(false)
                    .build();


            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)  // Returning 400 Bad Request for missing file
                    .body(errorMessage);
        }

        Video savevideo = videoService.save(video,file);




        if (savevideo != null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(savevideo);  // Success message with video info (optional)
        } else {

//            CustomMessage errorMessage = CustomMessage.builder()
//                    .message("Video upload not successful")
//                    .sucess(false)
//                    .build();

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }


    }

}
