package com.example.mygram.controller;


import com.example.mygram.model.entity.Photo;
import com.example.mygram.model.dto.request.PhotoRequest;
import com.example.mygram.model.dto.response.ResponseData;
import com.example.mygram.service.PhotoService;
import com.example.mygram.utils.FileUpload;
import jakarta.validation.Path;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;


@RestController
@RequestMapping(path = "/api/photo")
public class PhotoController {
    @Autowired
    private PhotoService photoService;

    @Autowired
    private FileUpload fileUpload;

    private ResponseData<Object> responseData;

    private final java.nio.file.Path fileStorageLocation = Paths.get("uploads");
    @PostMapping
    public ResponseEntity<?> addPhoto(
            @RequestParam("image") @Valid MultipartFile image,
            @RequestParam("description") String description) throws IOException {

        String filename = fileUpload.uploadImage(image, fileStorageLocation);
        PhotoRequest request = new PhotoRequest();

        request.setImage(filename);
        request.setDescription(description);

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
