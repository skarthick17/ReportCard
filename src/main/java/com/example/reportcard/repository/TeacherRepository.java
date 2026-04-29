package com.example.reportcard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.reportcard.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}