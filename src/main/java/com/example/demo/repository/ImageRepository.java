package com.example.demo.repository;

import com.example.demo.entity.dao.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface  ImageRepository extends JpaRepository<Image,Integer> {

}
