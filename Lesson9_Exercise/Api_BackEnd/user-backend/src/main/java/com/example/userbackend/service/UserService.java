package com.example.userbackend.service;

import com.example.userbackend.dto.UserDto;
import com.example.userbackend.entity.Image;
import com.example.userbackend.entity.User;
import com.example.userbackend.exception.BadRequestException;
import com.example.userbackend.exception.NotFoundException;
import com.example.userbackend.repository.ImageRepository;
import com.example.userbackend.repository.UserRepository;
import com.example.userbackend.request.CreateUserRequest;
import com.example.userbackend.request.UpdateAvatarRequest;
import com.example.userbackend.request.UpdatePasswordRequest;
import com.example.userbackend.request.UpdateUserRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailService mailService;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FileService fileService;

    @Autowired
    private ImageRepository imageRepository;

    public List<UserDto> getUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }


    public UserDto getUserById(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()) {
            return modelMapper.map(userOptional.get(), UserDto.class);
        }
        throw new NotFoundException("Not found todo with id = " + id);
    }

    public User createUser(CreateUserRequest request) {

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .avatar(request.getAvatar())
                .address(request.getAddress())
                .password(request.getPassword())
                .build();

        return userRepository.save(user);
    }

    public User updateUser(Integer id, UpdateUserRequest request) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found todo with id = " + id);
        });

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setAvatar(request.getAvatar());
        user.setAddress(request.getAddress());

        return userRepository.save(user);
    }

    public void deleteUser(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found todo with id = " + id);
        });
        userRepository.delete(user);
    }


    public void updateAvatar(Integer id, UpdateAvatarRequest request) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found todo with id = " + id);
        });
//        user.setAvatar(request.getAvatar());
//        userRepository.save(user);
        userRepository.updateAvatar(id, request.getAvatar());
    }

    public void updatePassword(Integer id, UpdatePasswordRequest request) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found todo with id = " + id);
        });
        //Ki???m tra xem password c?? c?? ch??nh x??c hay kh??ng
        if(!user.getPassword().equals(request.getOldPassword())){
            throw new BadRequestException("m???t kh???u c?? kh??ng ch??nh x??c");
        }
        //Ki???m tra xem password c?? c?? tr??ng nhau hay kh??ng
        if(request.getOldPassword().equals(request.getOldPassword())){
            throw  new BadRequestException("m???t kh???u c?? v?? m???i kh??ng ???????c tr??ng nhau");
        }

        user.setPassword(request.getNewPassword());
        userRepository.save(user);
    }

    public String forgotPassword(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found todo with id = " + id);
        });

        //Generate Password
        String newPassword = UUID.randomUUID().toString();

        user.setPassword(newPassword);
        userRepository.save(user);//l??u user v??o CSDL


        //Send Mail
        mailService.sendSimpleEmail(user.getEmail(),"Tran Dai Nghia Quen mat khau", "mat khau moi " + newPassword);
        return newPassword;
    }


    public String uploadFile(Integer id, MultipartFile file) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found todo with id = " + id);
        });
        return fileService.uploadFile(user,file);
    }

    public byte[] readFile(Integer id, Integer fileId) {
        Image image = imageRepository.findById(fileId).orElseThrow(()->{
            throw new NotFoundException("Not Found Image witd id = " + fileId);
        });
        return image.getData();
    }


//    public void deleteFile(Integer id, Integer fileId) {
//        User
//    }
}

