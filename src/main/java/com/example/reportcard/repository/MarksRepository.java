package com.example.reportcard.repository;

import java.util.Optional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.reportcard.model.Marks;

public interface MarksRepository extends JpaRepository<Marks, Long> {

    Optional<Marks> findByStudentIdAndSubjectAndMonth(Long studentId, String subject, String month);
    List<Marks> findByStudentIdAndMonth(Long studentId, String month);
    List<Marks> findByStudentId(Long studentId);
    void deleteByStudentId(Long studentId);
    @Modifying
    @Query(value = "TRUNCATE TABLE marks", nativeQuery = true)
    void truncateMarks();
    List<Marks> findByStudentIdInAndMonth(List<Long> studentIds, String month);
    
}