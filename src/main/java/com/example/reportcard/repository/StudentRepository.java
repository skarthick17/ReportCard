package com.example.reportcard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.reportcard.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByStudentClassAndSection(String studentClass, String section);
    void deleteByStudentClassAndSection(String studentClass, String section);
    @Modifying
    @Query(value = "TRUNCATE TABLE student", nativeQuery = true)
    void truncateStudents();
}