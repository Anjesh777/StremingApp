package com.example.videoServer.service.Impl;

import com.example.videoServer.model.video.Video;
import com.example.videoServer.service.videservice.VideoService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class VideoServiceimpl implements VideoService {

    @Value(("${files.video}"))
    String Dir;

    @PostConstruct
    public void init(){

        File file = new File(Dir);

        if (!file.exists()){
            file.mkdir();
            System.out.println("Folder Created");
        }else {
            System.out.println("Folder already Created");
        }

    }


    @Override
    public Video save(Video video, MultipartFile file) {

        try {
            String filename = file.getOriginalFilename();
            String contentType = file.getContentType();
            InputStream inputStream = file.getInputStream();

            String cleanFileName = StringUtils.cleanPath(filename);
            String cleanFolder = StringUtils.cleanPath(Dir);

            Path path= Paths.get(cleanFolder,cleanFileName);

            System.out.println(path);
            System.out.println(contentType);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return null;
    }

    @Override
    public Video get(String videoId) {
        return null;
    }

    @Override
    public Video getByTittle(String title) {
        return null;
    }

    @Override
    public List<Video> getAll() {
        return List.of();
    }
}