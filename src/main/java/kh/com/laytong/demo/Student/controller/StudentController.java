package kh.com.laytong.demo.Student.controller;

import kh.com.laytong.demo.Student.dto.RequestStudent;
import kh.com.laytong.demo.Student.dto.ResponseStudent;
import kh.com.laytong.demo.Student.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseStudent> addStudent(@RequestBody RequestStudent request){
        ResponseStudent student= studentService.registerStudent(request);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<List<ResponseStudent>> getByEmail(@PathVariable String email){
        List<ResponseStudent> students = studentService.queryByEmail(email);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

}

