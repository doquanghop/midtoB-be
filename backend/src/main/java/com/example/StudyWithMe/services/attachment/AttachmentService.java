package com.example.StudyWithMe.services.attachment;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.UUID;

@Service
public class AttachmentService implements IAttachmentService{

    private String uploadFile(File file, String fileName) throws IOException {
        BlobId blobId = BlobId.of("pjstudywithme.appspot.com", fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
        InputStream inputStream = AttachmentService.class.getClassLoader().getResourceAsStream("firebase-key.json");
        Credentials credentials = GoogleCredentials.fromStream(inputStream);
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));

        String DOWNLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/pjstudywithme.appspot.com/o/%s?alt=media";
        return String.format(DOWNLOAD_URL, URLEncoder.encode(fileName, StandardCharsets.UTF_8));
    }

    private File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
        File tempFile = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
            fos.close();
        }
        return tempFile;
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    @Override
    public String upload(MultipartFile multipartFile) {
        try {
            String fileName = multipartFile.getOriginalFilename();                        // to get original file name
            fileName = UUID.randomUUID().toString().concat(this.getExtension(fileName));  // to generated random string values for file name.

            File file = this.convertToFile(multipartFile, fileName);                      // to convert multipartFile to File
            String URL = this.uploadFile(file, fileName);                                   // to get uploaded file link
            file.delete();
            return URL;
        } catch (Exception e) {
            e.printStackTrace();
            return "Image couldn't upload, Something went wrong";
        }
    }
    @Override
    public void delete(String fileUrl){
        try {
            String fileName = extractFileName(fileUrl);
            InputStream inputStream = AttachmentService.class.getClassLoader().getResourceAsStream("firebase-key.json");
            Credentials credentials = GoogleCredentials.fromStream(inputStream);
            Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
            BlobId blobId = BlobId.of("pjstudywithme.appspot.com", fileName);
            boolean deleted = storage.delete(blobId);
            if (deleted) {
                System.out.println("File " + fileName + " has been deleted successfully.");
            } else {
                System.out.println("Failed to delete file " + fileName);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private static String extractFileName(String url) {
        // Tìm vị trí của ký tự '/' cuối cùng
        int lastIndex = url.lastIndexOf('/');
        // Kiểm tra nếu vị trí cuối cùng không phải là ký tự cuối cùng trong URL
        if (lastIndex != -1 && lastIndex < url.length() - 1) {
            // Tách phần URL trước ký tự '?' nếu có
            String fileNameWithParams = url.substring(lastIndex + 1);
            // Tìm vị trí của ký tự '?' trong phần tên tệp
            int questionMarkIndex = fileNameWithParams.indexOf('?');
            // Trả về phần tên tệp trước ký tự '?', nếu không tìm thấy thì trả về toàn bộ phần tên tệp
            return questionMarkIndex != -1 ? fileNameWithParams.substring(0, questionMarkIndex) : fileNameWithParams;
        }
        return null;
    }
}
