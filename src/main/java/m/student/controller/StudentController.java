package m.student.controller;

import m.student.model.serviceImp.StudentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import m.student.model.entity.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/student")
public class StudentController {
    @Autowired
    private StudentServiceImp studentServiceImp;
    @GetMapping("")
    public List<Student> getAllStudent(){
        return studentServiceImp.getAllStudent();
    }
    @GetMapping("/{studentId}")
    public Student findbyId(@PathVariable("studentId") int studentId){
        return studentServiceImp.findById(studentId);
    }
    @PostMapping()
    public Student createStudent(@RequestBody Student student){
        System.out.println("StudentNew"+student);
        return  studentServiceImp.saveorupdate(student);
    }
    @PutMapping("/{studentId}")
    public Student updateStudent(@RequestBody Student student,@PathVariable("studentId") int studentId){
        Student studentUpdate=studentServiceImp.findById(studentId);
        studentUpdate.setStudentName(student.getStudentName());
        studentUpdate.setStudentAge(student.getStudentAge());
        studentUpdate.setStudentStatus(student.isStudentStatus());
   return  studentServiceImp.saveorupdate(studentUpdate);
    }
    @DeleteMapping("/{studentId}")
    public void deleteStudent(@PathVariable("studentId") int studentId){
        studentServiceImp.deleteStudent(studentId);
    }
    @GetMapping("/searchStudentByName")
    public  List<Student> searchByName(@RequestParam("name") String name){
        return studentServiceImp.searchByName(name);
    }

    @GetMapping("/sortStudentByName")
    public  List<Student> sortStudentByName(@RequestParam("diraction") String diraction){
        return studentServiceImp.sortStudentByStudentName(diraction);
    }

    @GetMapping("/sortStudentByAge")
    public  List<Student> sortStudentByAge(@RequestParam("diraction") String diraction){
        return studentServiceImp.sortStudentByStudentAge(diraction);
    }

    @GetMapping("/pagging")
    public ResponseEntity<Map<String,Object>> pagging(@RequestParam(defaultValue = "0") int pagging,
                                                      @RequestParam(defaultValue = "5") int size){
        Pageable pageable= PageRequest.of(pagging,size);
        Page<Student> studentPage=studentServiceImp.pagging(pageable);
        Map<String,Object> data=new HashMap<>();
        data.put("Students",studentPage.getContent());
        data.put("Total",studentPage.getSize());
        data.put("TotalItem",studentPage.getTotalElements());
        data.put("TotalPage",studentPage.getTotalPages());
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
