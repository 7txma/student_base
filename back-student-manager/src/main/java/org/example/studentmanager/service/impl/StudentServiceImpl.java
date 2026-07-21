package org.example.studentmanager.service.impl;

import org.example.studentmanager.entity.Student;
import org.example.studentmanager.mapper.StudentMapper;
import org.example.studentmanager.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> findAll() {
        return studentMapper.selectAll();
    }

    @Override
    public int add(Student student) {
        return studentMapper.insert(student);
    }

    @Override
    public int modify(Student student) {
        return studentMapper.update(student);
    }

    @Override
    public int remove(String studentId) {
        return studentMapper.delete(studentId);
    }
}