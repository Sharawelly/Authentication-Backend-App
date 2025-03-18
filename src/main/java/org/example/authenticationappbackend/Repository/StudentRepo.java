package org.example.authenticationappbackend.Repository;

import org.example.authenticationappbackend.Entity.ImageData;
import org.example.authenticationappbackend.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
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



    @Transactional
    @Modifying
    @Query("UPDATE Student s SET s.password = :password WHERE s.id = :id")
    int changePassword(@Param("id") int id,

                      @Param("password") String password);


    @Transactional
    @Modifying
    @Query("UPDATE Student s SET s.name = :name, s.gender = :gender, s.level = :level WHERE s.id = :id")
    int updateStudent(@Param("id") int id,
                      @Param("level") int level,
                      @Param("gender") String gender,
                      @Param("name") String name);


    @Query("SELECT COUNT(s) FROM Student s WHERE s.id = :id")
    int idExists(@Param("id") int id);




}
