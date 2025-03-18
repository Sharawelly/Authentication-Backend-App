package org.example.authenticationappbackend.Controller;

import org.example.authenticationappbackend.Entity.ImageData;
import org.example.authenticationappbackend.Entity.Student;
import org.example.authenticationappbackend.Repository.ImageDataRepo;
import org.example.authenticationappbackend.Services.ImageDataService;
import org.example.authenticationappbackend.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/{studentId}")
public class ImageDataController {

    @Autowired
    private ImageDataService imageDataService;

    @Autowired
    private StudentService studentService;

    @PostMapping("/addImage")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file, @PathVariable int studentId) throws IOException {
        String upload = imageDataService.uploadImage(file, studentId);
        return ResponseEntity.status(HttpStatus.OK).body(upload);
    }

    @GetMapping("/getImage")
    public ResponseEntity<?> downloadImage(@PathVariable int studentId){
        Student student = studentService.getStudent(studentId);
        ImageData imageData = imageDataService.getImageData(student); // consists of 3 things
        byte[] imageFile = imageDataService.downloadImage(student);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf(imageData.getImageType())).body(imageFile);

    }

    @GetMapping("/delImage")
    public void deleteImage (@PathVariable int studentId){
        imageDataService.deleteImage(studentId);
    }




}
