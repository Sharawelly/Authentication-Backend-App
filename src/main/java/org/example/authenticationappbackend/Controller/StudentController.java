package org.example.authenticationappbackend.Controller;

import org.example.authenticationappbackend.Entity.Student;
import org.example.authenticationappbackend.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping()
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/IDexists")
    public boolean checkID(@RequestBody Map<String, Object> body) {
        int id = (int) body.get("id");
        return studentService.checkID(id);
    }

    @PostMapping("/addStudent")
    public Student addStudent(@RequestBody Student student){
        return studentService.saveDetails(student);
    }

    @PostMapping("/getStudent")
    public Student checkLogedin(@RequestBody Map<String, Object> body){
        return studentService.isLogedIn(body);
    }

    @GetMapping("/getStudents")
    public List<Student> getAllStudent(){
        return studentService.getAllStudents();
    }

    @GetMapping("/getStudentsIds")
    public List<Integer> getAllStudentIds(){
        return studentService.getAllStudentsId();
    }

    @PostMapping("/deleteStudent")
    public void deleteStudent(@RequestBody Map<String, Object> body){
        studentService.deleteStudent(body);
    }
    @GetMapping("/deleteStudents")
    public void deleteAllStudent(){
        studentService.deleteAllStudent();
    }




    @PostMapping("/editStudent")
    public void editStudent(@RequestBody Map<String, Object> body){
        studentService.editStudent(body);
    }



    @PostMapping("/changePassword")
    public void changePassword(@RequestBody Map<String, Object> body){
        studentService.changePassword(body);
    }


}
