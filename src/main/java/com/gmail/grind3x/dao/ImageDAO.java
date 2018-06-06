package com.gmail.grind3x.dao;

import com.gmail.grind3x.model.Image;

public interface ImageDAO {

    void insertImage(Image image);

    void deleteImage(Image image);

    Image findImageByName(String name);

    void updateImage(Image image);
}
