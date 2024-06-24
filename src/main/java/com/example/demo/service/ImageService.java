package com.example.demo.service;

import com.example.demo.entity.dao.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    Image saveImage(Image image);

    String saveAndGetAvatarUrl(MultipartFile avatar) throws IOException;

}
