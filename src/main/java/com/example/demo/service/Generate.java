package com.example.demo.service;

import java.util.List;
import java.util.Optional;

public interface Generate <E>{
    List<E> findAll();

    Optional<E> findById(Integer id);

    E save(E e);

    void deleteById(Integer id);
}
