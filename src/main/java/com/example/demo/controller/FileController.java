package com.example.demo.controller;

import com.example.demo.auth.response.CustomResponse;
import com.example.demo.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/images")
public class FileController {
    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private FileStorageService fileStorageService;

    //Dang loi
    @GetMapping(value = "/avatar/{avatarName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<?> getAvatar(@PathVariable("avatarName") String avatarName)
    {
        try {
            Resource resource = resourceLoader.getResource("file:" + fileStorageService.getUrlAvatar() + "/" + avatarName);
            if (resource.exists()){
                return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(resource);
            }
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(null);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new CustomResponse<>(0, null, e.getMessage()));
        }
    }

}
