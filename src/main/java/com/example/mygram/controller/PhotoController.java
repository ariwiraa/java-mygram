package com.example.mygram.controller;


import com.example.mygram.model.entity.Photo;
import com.example.mygram.model.dto.request.PhotoRequest;
import com.example.mygram.model.dto.response.ResponseData;
import com.example.mygram.service.PhotoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping(path = "/api/photo")
public class PhotoController {
    @Autowired
    private PhotoService photoService;

    private ResponseData<Object> responseData;

    @PostMapping
    public ResponseEntity<?> addPhoto(@RequestBody @Valid PhotoRequest request) {
        Photo photo = photoService.addPhotoService(request);

        responseData = new ResponseData<Object>(201, "Success", photo);
        return ResponseEntity.status(responseData.getCode()).body(responseData);
    }

    @GetMapping
    public ResponseEntity<?> getPhotos() {
        List<Photo> photos = photoService.getPhotosService();

        responseData = new ResponseData<Object>(200, "success", photos);
        return ResponseEntity.status(responseData.getCode()).body(responseData);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getPhoto(@PathVariable Integer id) throws Exception{
        Photo photo = photoService.getPhotoService(id);

        responseData = new ResponseData<Object>(200, "success", photo);
        return ResponseEntity.status(responseData.getCode()).body(responseData);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updatePhoto(@PathVariable Integer id, @RequestBody @Valid PhotoRequest request) throws Exception {
        Photo photo = photoService.updatePhotoService(id, request);

        responseData = new ResponseData<Object>(200, "success", photo);
        return ResponseEntity.status(responseData.getCode()).body(responseData);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deletePhoto(@PathVariable Integer id) throws Exception {
        photoService.deletePhoto(id);

        responseData = new ResponseData<Object>(200, "success", null);
        return ResponseEntity.status(responseData.getCode()).body(responseData);
    }


}
