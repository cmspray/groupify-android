package com.groupify.groupify.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GroupifyImage {

    @SerializedName("height")
    @Expose
    private String height;
    @SerializedName("url")
    @Expose
    private String imageUrl;
    @SerializedName("width")
    @Expose
    private String width;

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String displayName) {
        this.width = width;
    }
}
