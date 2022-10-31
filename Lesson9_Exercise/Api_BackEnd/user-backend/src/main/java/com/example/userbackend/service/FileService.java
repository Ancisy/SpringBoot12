package com.example.userbackend.service;

import com.example.userbackend.entity.Image;
import com.example.userbackend.entity.User;
import com.example.userbackend.exception.BadRequestException;
import com.example.userbackend.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class FileService {

    @Autowired
    private ImageRepository imageRepository;
    //svg
    public String uploadFile(User user, MultipartFile file){
        validate(file);
        try {
            Image image = new Image();
            image.setUploadedAt(LocalDateTime.now());
            image.setData(file.getBytes());
            image.setUser(user);

            imageRepository.save(image);
            return "api/v1/user/" + user.getId() + "/files/" + image.getId();
        } catch (IOException e) {
            throw new RuntimeException("Lỗi khi upload file");
        }

    }

    public void validate(MultipartFile file){
        String fileName = file.getOriginalFilename();
        //Kiểm tra tên file
        if(fileName == null || fileName.equals("")){
            throw new BadRequestException("tên file không hợp lệ");
        }
        //Kiểm tra đuôi file
        String fileExtension = getFileExtension(fileName);
        if(!checkFileExtension(fileExtension)){
            throw new BadRequestException("Định dạng file không hợp lệ");
        }
//        //Kiểm tra dung lượng file <= 2MB
//        if(file.getSize()/(1024*1024)>2)
    }

    private String getFileExtension(String fileName){
        int lastIndexOf = fileName.lastIndexOf(".");
        if(lastIndexOf == -1){
            return "";
        }
        return fileName.substring(lastIndexOf + 1);
    }

    private boolean checkFileExtension(String fileExtension){
        List<String> extensions = Arrays.asList("jpg","png","jpeg");
        return extensions.contains(fileExtension.toLowerCase());
    }
}
