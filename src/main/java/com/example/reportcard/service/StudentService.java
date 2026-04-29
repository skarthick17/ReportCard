package com.example.reportcard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.reportcard.model.Student;
import com.example.reportcard.repository.MarksRepository;
import com.example.reportcard.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private MarksRepository marksRepository;

    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    public List<Student> getStudents(String studentClass, String section) {
        return studentRepository.findByStudentClassAndSection(studentClass, section);
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }
    public void deleteClass(String studentClass, String section) {

        List<Student> students = studentRepository.findByStudentClassAndSection(studentClass, section);

        for(Student s : students) {
            marksRepository.deleteByStudentId(s.getId());
        }

        studentRepository.deleteByStudentClassAndSection(studentClass, section);
    }
}
