package org.example.authenticationappbackend.Repository;

import org.example.authenticationappbackend.Entity.ImageData;
import org.example.authenticationappbackend.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface StudentRepo extends JpaRepository<Student, Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE Student s SET s.imageData = :imageData WHERE s.id = :studentId")
    int updateStudentById(int studentId, ImageData imageData);


    @Transactional
    @Modifying
    @Query("UPDATE Student s SET s.imageData = null WHERE s.id = :studentId")
    int deleteStudentImageById(int studentId);


    @Query("SELECT s.id FROM Student s")
    List<Integer> getAllStudentsId();
}
