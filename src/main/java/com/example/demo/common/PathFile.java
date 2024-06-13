package com.example.demo.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import java.nio.file.Path;
import java.nio.file.Paths;
@SpringBootApplication
@ComponentScan
@PropertySource("classpath:application.properties")
public class PathFile {
    @Value("${rootFileUploads}")
    private String rootFileUploads;

    public Path getPathAvatar(){
        return Paths.get(rootFileUploads, "avatar");
    }
    public Path getPathVideo(){
        return Paths.get(rootFileUploads, "video");
    }
    public Path getPathSvg(){
        return Paths.get(rootFileUploads, "svg");
    }
}

