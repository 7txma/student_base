package org.example.studentmanager.mapper;

import org.example.studentmanager.entity.Student;
import java.util.List;

public interface StudentMapper {
    List<Student> selectAll();
    int insert(Student student);
    int update(Student student);
    int delete(String studentId);
}