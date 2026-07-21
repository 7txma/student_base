package org.example.studentmanager.controller;

import org.example.studentmanager.entity.Student;
import org.example.studentmanager.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "http://localhost:5173") // 明确允许前端地址
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getAll() {
        return studentService.findAll();
    }

    @PostMapping
    public int add(@RequestBody Student student) {
        // 直接返回影响行数，1 表示成功，0 表示失败
        return studentService.add(student);
    }

    @PutMapping("/{studentId}")
    public int update(@PathVariable String studentId, @RequestBody Student student) {
        student.setStudentId(studentId);
        return studentService.modify(student);
    }

    @DeleteMapping("/{studentId}")
    public int delete(@PathVariable String studentId) {
        return studentService.remove(studentId);
    }
}