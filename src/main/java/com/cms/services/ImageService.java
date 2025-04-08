package com.cms.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface ImageService {

    String uploadImage(MultipartFile contactPhoto);
    String getUrlFromPublicId(String publicid);
}
