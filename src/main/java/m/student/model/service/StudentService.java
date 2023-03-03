package m.student.model.service;

import m.student.model.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudent();

    Student findById(int studentId);

    Student saveorupdate(Student student);

    void deleteStudent(int studentId);

    List<Student> searchByName(String name);

    List<Student> sortStudentByStudentName(String diraction);

    List<Student> sortStudentByStudentAge(String diraction);

Page<Student> pagging(Pageable pageable);

}
