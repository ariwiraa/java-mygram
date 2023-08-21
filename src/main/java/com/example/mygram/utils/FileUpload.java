package com.example.mygram.utils;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;

@Component
public class FileUpload {
    private static final List<String> ALLOWED_IMAGE_EXTENSIONS = Arrays.asList("jpg", "jpeg", "png");
    private static final long MAX_IMAGE_SIZE_BYTES = 2 * 1024 * 1024; // 2 MB

    public String uploadImage(MultipartFile file, Path targetLocation) throws IOException {
        if (!Files.exists(targetLocation)){
            Files.createDirectory(targetLocation);
        }

        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        validateImageFile(file);

        Path filepath = targetLocation.resolve(filename);
        System.out.println(filepath);
        Files.copy(file.getInputStream(), filepath, StandardCopyOption.REPLACE_EXISTING);
        return filename;
    }

    private void validateImageFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is Empty");
        }

        String fileExtension = getFileExtension(file.getOriginalFilename());
        if (!ALLOWED_IMAGE_EXTENSIONS.contains(fileExtension.toLowerCase())) {
            throw new IllegalArgumentException("Invalid image format");
        }

        if (file.getSize() > MAX_IMAGE_SIZE_BYTES) {
            throw new IllegalArgumentException("File size exceeds the limit");
        }
    }

    private String getFileExtension(String filename) {
        int lastDotIndex = filename.lastIndexOf(".");
        if (lastDotIndex == -1) {
            throw new IllegalArgumentException("File has no extension");
        }
        return filename.substring(lastDotIndex + 1);
    }
}
