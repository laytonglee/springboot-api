package kh.com.laytong.demo.Student.service;

import kh.com.laytong.demo.Department.entity.DepartmentEntity;
import kh.com.laytong.demo.Department.repository.DepartmentRepository;
import kh.com.laytong.demo.Student.dto.RequestStudent;
import kh.com.laytong.demo.Student.dto.ResponseStudent;
import kh.com.laytong.demo.Student.entity.StudentEntity;
import kh.com.laytong.demo.Student.repository.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;

    public StudentService(StudentRepository studentRepository,
                          DepartmentRepository departmentRepository) {
        this.studentRepository = studentRepository;
        this.departmentRepository = departmentRepository;
    }

    public ResponseStudent registerStudent(RequestStudent request) {

        DepartmentEntity department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new RuntimeException(
                        "Department not found with id: " + request.getDepartmentId()
                ));

        StudentEntity student = new StudentEntity();
        student.setEmail(request.getEmail());
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setEnrollmentDate(request.getEnrollmentDate());
        student.setDepartmentEntity(department);

        StudentEntity saved = studentRepository.save(student);

        ResponseStudent response = new ResponseStudent();
        response.setId(student.getId());
        response.setFirstName(saved.getFirstName());
        response.setLastName(saved.getLastName());
        response.setDepartmentName(department.getName());

        return response;

    }
    public List<ResponseStudent> queryByEmail(String email){
        List<StudentEntity> students = studentRepository.findAllByEmail(email);

        //            ResponseStudent res = new ResponseStudent();
        //
        //            res.setId(student.getId());
        //            res.setFirstName(student.getFirstName());
        //            res.setLastName(student.getLastName());
        //            res.setDepartmentName(student.getDepartmentEntity().getName());
        //
        //            return res;
        return students.stream().map(ResponseStudent::fromEntity).toList();
    }


    public List<ResponseStudent> queryByDepartmentId (Long departmentId){
        List<StudentEntity> results = studentRepository.findAllByDepartmentEntity_Id(departmentId);

        return results.stream().map(ResponseStudent::fromEntity).toList();

    }
}
