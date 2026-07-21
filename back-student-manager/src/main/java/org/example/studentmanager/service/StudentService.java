package org.example.studentmanager.service;

import org.example.studentmanager.entity.Student;
import java.util.List;

public interface StudentService {
    List<Student> findAll();
    int add(Student student);
    int modify(Student student);
    int remove(String studentId);
}