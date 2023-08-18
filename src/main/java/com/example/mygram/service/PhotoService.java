package com.example.mygram.service;

import com.example.mygram.model.Photo;
import com.example.mygram.model.dto.PhotoRequest;

import java.util.List;

public interface PhotoService {
    Photo addPhotoService(PhotoRequest request);
    List<Photo> getPhotosService();

    Photo getPhotoService(Integer id) throws Exception;

    Photo updatePhotoService(Integer id, PhotoRequest request) throws Exception;

    void deletePhoto(Integer id) throws Exception;
}
