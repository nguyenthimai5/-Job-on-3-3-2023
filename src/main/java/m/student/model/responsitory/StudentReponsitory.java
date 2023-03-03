package m.student.model.responsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import m.student.model.entity.Student;

import java.util.List;

@Repository
public interface StudentReponsitory extends JpaRepository<Student,Integer> {
    List<Student> findByStudentNameContaining(String name);

}
