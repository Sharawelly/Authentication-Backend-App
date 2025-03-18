package org.example.authenticationappbackend.Services;

import org.example.authenticationappbackend.Entity.ImageData;
import org.example.authenticationappbackend.Entity.Student;
import org.example.authenticationappbackend.ImageUtils;
import org.example.authenticationappbackend.Repository.ImageDataRepo;
import org.example.authenticationappbackend.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageDataService {
    @Autowired
    private ImageDataRepo imageDataRepo;

    @Autowired
    private StudentRepo studentRepo;

    public String uploadImage(MultipartFile file, int studentId) throws IOException {

        ImageData imageData = new ImageData(
                file.getOriginalFilename(),
                file.getContentType(),
                file.getBytes()
        );
        if (imageData != null) {
            imageDataRepo.save(imageData);
            Student student = studentRepo.findById(studentId).orElse(null);
            studentRepo.updateStudentById(studentId, imageData);
            if (student.getImageData() != null){
                imageDataRepo.deleteImageById(student.getImageData().getId());
            }


            return "file uploaded successfully : " + file.getOriginalFilename();
        }
        return null;
    }

    public void deleteImage(int studentId){
        Student student = studentRepo.findById(studentId).orElse(null);
        if (student.getImageData() != null){
            studentRepo.deleteStudentImageById(studentId);
            imageDataRepo.deleteImageById(student.getImageData().getId());
        }
    }



    public ImageData getImageData(Student student) {
        return imageDataRepo.findById(student.getImageData().getId()).orElse(null);
    }


    public byte[] downloadImage(Student student) {
        if (student != null) {
            ImageData dbImageData = imageDataRepo.findById(student.getImageData().getId()).orElse(null);
            if (dbImageData != null) {
                return dbImageData.getImageData(); // only the data
            }
        }
        return null;
    }

//    public void deleteImage(Student student) {
//        ImageData imageData = student.getImageData();
//        if (imageData != null) {
//            studentRepo.deleteStudentImageById(student.getId());
//            imageDataRepo.delete(imageData);
//        }
//    }
}
