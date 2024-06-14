package com.example.demo.service.impl;

import com.example.demo.common.PathFile;
import com.example.demo.service.FileStorageService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileStorageServiceImpl implements FileStorageService {
    @Autowired
    private PathFile pathFile;

    @Override
    public void initAvatarResources() {
        try {
            Files.createDirectories(pathFile.getPathAvatar());
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public boolean saveAvatar(MultipartFile file, String uuid) throws UnsupportedEncodingException {
        initAvatarResources();
        try (OutputStream outputStream = Files.newOutputStream(pathFile.getPathAvatar().resolve(uuid));
             InputStream inputStream = file.getInputStream()) {
            IOUtils.copy(inputStream, outputStream);

            return true;
        } catch (IOException e) {
            throw new RuntimeException("Could not save the file. Error: " + e.getMessage());
        }
    }

    @Override
    public Path getUrlAvatar() {
        return pathFile.getPathAvatar();
    }
}
