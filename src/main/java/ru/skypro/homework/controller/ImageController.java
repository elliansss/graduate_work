package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.service.UserService;

import java.io.IOException;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/image")
@RequiredArgsConstructor

public class ImageController {

    private UserService userService;

    @PatchMapping(consumes = "multipart/form-data")
    public ResponseEntity<?> updateUserImage(@RequestParam("image") MultipartFile image,
                                             Authentication authentication) throws IOException {
        userService.updateUserImage(authentication.getName(), image);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable("id") String path, Authentication authentication)
            throws IOException {
        System.out.println("Получение аватара пользователя");
        byte[] imageBytes = userService.getUserImage(authentication.getName());
        return ResponseEntity.ok(imageBytes);
    }
}
