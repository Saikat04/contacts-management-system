package com.cms.services.impl;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.cms.helpers.AppConstants;
import com.cms.services.ImageService;

@Service
public class ImageServiceImpl implements ImageService{
    @Autowired
    private Cloudinary cloudinary;
    @Override
    public String uploadImage(MultipartFile contactPhoto) {
        String fileName = UUID.randomUUID().toString();
        try {
            byte[] data = new byte[contactPhoto.getInputStream().available()];
            contactPhoto.getInputStream().read(data);
            cloudinary.uploader().upload(data, ObjectUtils.asMap(
                "public_id", fileName
            ));
            return this.getUrlFromPublicId(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }        
    }
    @Override
    public String getUrlFromPublicId(String publicid) {
        return cloudinary
        .url()
        .transformation(
            new Transformation<>()
            .width(AppConstants.WIDTH_PHOTO)
            .height(AppConstants.HEIGHT_PHOTO)
            .crop(AppConstants.CROP_PHOTO)
        )
        .generate(publicid);
    }

}
