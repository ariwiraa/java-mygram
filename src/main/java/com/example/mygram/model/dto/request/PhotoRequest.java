package com.example.mygram.model.dto.request;

import jakarta.validation.constraints.NotBlank;

public class PhotoRequest {
    @NotBlank(message = "Image Not Blank")
    private String image;

    private String description;

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
