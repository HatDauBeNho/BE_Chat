package com.example.demo.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.nio.file.Path;

public interface FileStorageService {
    public void initAvatarResources();
    public boolean saveAvatar(MultipartFile file, String uuid) throws UnsupportedEncodingException;
    public Path getUrlAvatar();
}
