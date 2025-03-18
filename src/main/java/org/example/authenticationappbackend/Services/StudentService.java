package org.example.authenticationappbackend.Services;

import org.example.authenticationappbackend.Entity.Student;
import org.example.authenticationappbackend.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;

    public Student saveDetails(Student student){
        return studentRepo.save(student);
    }

    public Student isLogedIn(Map<String, Object> body){
        int id = (int)body.get("id");
        String password = (String)body.get("password");
        Student student = studentRepo.findById(id).orElse(null);
        if(student != null && student.getPassword().equals(password)){
            return student;
        }
        return null;
    }

    public Student getStudent(int studentId){
        Student student = studentRepo.findById(studentId).orElse(null);
        return student;
    }

    public List<Student> getAllStudents(){
        return studentRepo.findAll();
    }

    public List<Integer> getAllStudentsId(){
        return studentRepo.getAllStudentsId();
    }

    public void deleteStudent(Map<String, Object> body){
        int id = (int)body.get("id");
        Student student = studentRepo.findById(id).orElse(null);
        studentRepo.delete(student);
    }

    public void deleteAllStudent(){
        studentRepo.deleteAll();
    }


    public void editStudent(Map<String, Object> body) {
        // name , gender , level , password
        String name= (String)body.get("name");
        String gender= (String)body.get("gender");
        int level= (int)body.get("level");
        int id= (int)body.get("id");


        studentRepo.updateStudent(id, level, gender, name);




    }


    public void changePassword(Map<String, Object> body) {
        int id= (int)body.get("id");
        String password= (String)body.get("password");


            studentRepo.changePassword(id,password);




    }


    }
