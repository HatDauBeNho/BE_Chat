package com.example.demo.service.impl;

import com.example.demo.entity.dao.Image;
import com.example.demo.repository.ImageRepository;
import com.example.demo.service.FileStorageService;
import com.example.demo.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    ImageRepository imageRepository;
    @Override
    public Image saveImage(Image image) {

        return imageRepository.save(image);
    }

    @Override
    public String saveAndGetAvatarUrl(MultipartFile avatar) throws IOException {
        String uuid = UUID.randomUUID().toString();
        fileStorageService.saveAvatar(avatar,uuid);
        return "/api/images/avatar/" + uuid;
    }

}
