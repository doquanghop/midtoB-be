package com.example.StudyWithMe.services.attachment;

import org.springframework.web.multipart.MultipartFile;

public interface IAttachmentService {
    String upload(MultipartFile multipartFile);
    void delete(String fileUrl);
}
