package kh.com.laytong.demo.Student.controller;

import kh.com.laytong.demo.Student.dto.RequestStudent;
import kh.com.laytong.demo.Student.dto.ResponseStudent;
import kh.com.laytong.demo.Student.entity.StudentEntity;
import kh.com.laytong.demo.Student.entity.StudentStatus;
import kh.com.laytong.demo.Student.service.StudentService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseStudent> addStudent(@RequestBody RequestStudent request){
        ResponseStudent registerStudent = studentService.registerStudent(request);
        return new ResponseEntity<>(registerStudent, HttpStatus.CREATED);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<List<ResponseStudent>> getByEmail(@PathVariable String email){
        List<ResponseStudent> studentsWithMail = studentService.queryByEmail(email);
        return new ResponseEntity<>(studentsWithMail, HttpStatus.OK);
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<ResponseStudent>> getByDepartment (@PathVariable Long departmentId){
        List<ResponseStudent> studentsWithDpId = studentService.queryByDepartmentId(departmentId);
        return new ResponseEntity<>(studentsWithDpId, HttpStatus.OK);
    }

    @GetMapping("null-status")
    public ResponseEntity<List<ResponseStudent>> getByNullStatus (){
        List<ResponseStudent> studentsWithNullStatus = studentService.queryByNullStatus();
        return new ResponseEntity<>(studentsWithNullStatus,HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ResponseStudent>> getByStatus (@PathVariable StudentStatus status) {

        List<ResponseStudent> studentWithStatus = studentService.queryByStatus(status);

        return new ResponseEntity<>(studentWithStatus, HttpStatus.OK);

    }

    @GetMapping("/department/{departmentId}/status/{status}")
    public ResponseEntity<List<ResponseStudent>> getByDepartmentAndStatus(@PathVariable Long departmentId, @PathVariable  StudentStatus status){

        List<ResponseStudent> studentWithDepartmentAndStatus = studentService.queryByDepartmentIdAndStatus(departmentId, status);

        return new ResponseEntity<>(studentWithDepartmentAndStatus, HttpStatus.OK);
    }
//
//    @GetMapping("/start-date")
//    public ResponseEntity<List<ResponseStudent>> getByDate() {
//        List<ResponseStudent> studentWithDate =  studentService.queryByDate();
//        return new ResponseEntity<>(studentWithDate, HttpStatus.OK);
//    }



}

