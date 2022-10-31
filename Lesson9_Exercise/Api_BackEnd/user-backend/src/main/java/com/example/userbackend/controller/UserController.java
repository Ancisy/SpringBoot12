package com.example.userbackend.controller;

import com.example.userbackend.dto.UserDto;
import com.example.userbackend.entity.User;
import com.example.userbackend.request.CreateUserRequest;
import com.example.userbackend.request.UpdateAvatarRequest;
import com.example.userbackend.request.UpdatePasswordRequest;
import com.example.userbackend.request.UpdateUserRequest;
import com.example.userbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public List<UserDto> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getTodoById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/newUser")
    public User createUser(@RequestBody CreateUserRequest request){
        return userService.createUser(request);
    }

    @PutMapping("user/{id}")
    public User updateUser(@RequestBody UpdateUserRequest request, @PathVariable Integer id){
        return userService.updateUser(id, request);
    }

    @DeleteMapping("/user/delete/{id}")
    public void deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
    }

    //Upload ảnh
    @PutMapping("user/{id}/updateImage")
    @ResponseStatus(HttpStatus.NO_CONTENT)//204
    public void updateImage(@RequestBody UpdateAvatarRequest request, @PathVariable Integer id){
         userService.updateAvatar(id, request);
    }

    @PutMapping("user/{id}/updatePassword")
    @ResponseStatus(HttpStatus.NO_CONTENT)//204
    public void updatePassword(@RequestBody UpdatePasswordRequest request, @PathVariable Integer id){
        userService.updatePassword(id, request);
    }

    //Quên mật khẩu
    @PostMapping("user/{id}/forgotPassword")
    public String forgotPassword(@PathVariable Integer id){
        return userService.forgotPassword(id);
    }

    //Upload File
    @PostMapping("user/{id}/files")
    public String uploadFile(@PathVariable Integer id, @ModelAttribute("file") MultipartFile file){
        return userService.uploadFile(id,file);
    }

    @GetMapping(value = "user/{id}/files/{fileId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] readFile(@PathVariable Integer id,@PathVariable Integer fileId){
        return userService.readFile(id,fileId);
    }


//    @DeleteMapping(value = "user/{id}/files/{fileId"))
//    public void deleteFile(@PathVariable Integer id, @PathVariable Integer fileId){
//        userService.deleteFile(id,fileId);
//    }

    //Sắp xếp ngày tạo mới nhất trên đầu

}
