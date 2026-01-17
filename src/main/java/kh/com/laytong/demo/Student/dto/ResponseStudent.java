package kh.com.laytong.demo.Student.dto;

import kh.com.laytong.demo.Student.entity.StudentEntity;
import lombok.*;

import java.time.LocalDate;

@Data
public class ResponseStudent {
    private Long id;
    private String firstName;
    private String lastName;
    private String departmentName;

    public static ResponseStudent fromEntity(StudentEntity student) {
        ResponseStudent responseStudent = new ResponseStudent();
        responseStudent.setId(student.getId());
        responseStudent.setDepartmentName(student.getDepartmentEntity().getName());
        responseStudent.setFirstName(student.getFirstName());
        return responseStudent;
    }
}
