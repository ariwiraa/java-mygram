package com.example.mygram.service.impl;

import com.example.mygram.exception.NotFoundException;
import com.example.mygram.model.entity.Photo;
import com.example.mygram.model.dto.request.PhotoRequest;
import com.example.mygram.repository.PhotoRepository;
import com.example.mygram.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoRepository photoRepository;
    @Override
    public Photo addPhotoService(PhotoRequest request) {
        Photo photo = new Photo();
        photo.setImage(request.getImage());
        photo.setDescription(request.getDescription());

        photo = photoRepository.save(photo);

        return photo;
    }

    @Override
    public List<Photo> getPhotosService() {
        return photoRepository.findAll();
    }

    @Override
    public Photo getPhotoService(Integer id) throws Exception {
        Optional<Photo> optionalPhoto = photoRepository.findById(id);

        if (optionalPhoto.isPresent()) {
            return optionalPhoto.get();
        } else {
            throw new NotFoundException("Photo Not Found");
        }

    }

    @Override
    public Photo updatePhotoService(Integer id, PhotoRequest request) throws Exception {
        Optional<Photo> optionalPhoto = photoRepository.findById(id);
        Photo photo;
        if (optionalPhoto.isPresent()) {
            photo = optionalPhoto.get();
        } else {
            throw new NotFoundException("Photo Not Found");
        }

        photo.setImage(request.getImage());
        photo.setDescription(request.getDescription());

        photo = photoRepository.save(photo);

        return photo;
    }

    @Override
    public void deletePhoto(Integer id) throws Exception {
        Optional<Photo> optionalPhoto = photoRepository.findById(id);
        Photo photo;
        if (optionalPhoto.isPresent()) {
            photo = optionalPhoto.get();
        } else {
            throw new NotFoundException("Photo Not Found");
        }

        photoRepository.delete(photo);
    }
}
