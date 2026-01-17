package kh.com.laytong.demo.Student.repository;

import kh.com.laytong.demo.Student.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    List<StudentEntity> findAllByEmail(String email);
}
