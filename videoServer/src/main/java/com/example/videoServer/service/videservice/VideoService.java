package com.example.videoServer.service.videservice;

import com.example.videoServer.model.video.Video;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VideoService {


    Video save(Video video, MultipartFile file);
    Video get(String videoId);
    Video getByTittle(String title);
    List<Video>getAll();



}
