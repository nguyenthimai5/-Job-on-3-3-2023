package m.student.model.serviceImp;

import m.student.model.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import m.student.model.entity.Student;
import m.student.model.responsitory.StudentReponsitory;

import java.util.List;

@Service
public class StudentServiceImp implements StudentService {
    @Autowired
    private StudentReponsitory studentReponsitory;

    @Override
    public List<Student> getAllStudent() {
        return studentReponsitory.findAll();
    }

    @Override
    public Student findById(int studentId) {
        return studentReponsitory.findById(studentId).get();
    }

    @Override
    public Student saveorupdate(Student student) {
        return studentReponsitory.save(student);
    }

    @Override
    public void deleteStudent(int studentId) {
        studentReponsitory.deleteById(studentId);
    }

    @Override
    public List<Student> searchByName(String name) {
        return studentReponsitory.findByStudentNameContaining(name);
    }

    @Override
    public List<Student> sortStudentByStudentName(String diraction) {
        if (diraction.equals("asc")) {
            return studentReponsitory.findAll(Sort.by("studentName").ascending());
        } else {
            return studentReponsitory.findAll(Sort.by("studentName").descending());
        }
    }

    @Override
    public List<Student> sortStudentByStudentAge(String diraction) {
        if (diraction.equals("asc")) {
            return studentReponsitory.findAll(Sort.by("studentAge").ascending());
        } else {
            return studentReponsitory.findAll(Sort.by("studentAge").descending());
        }
    }

    @Override
    public Page<Student> pagging(Pageable pageable) {
        return studentReponsitory.findAll(pageable);
    }
}
