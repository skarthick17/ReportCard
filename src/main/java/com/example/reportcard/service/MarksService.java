package com.example.reportcard.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.example.reportcard.model.Marks;
import com.example.reportcard.repository.MarksRepository;

@Service
public class MarksService {

    @Autowired
    private MarksRepository marksRepository;
    
    public List<Marks> getMarksByStudentsAndMonth(List<Long> studentIds, String month) {
        return marksRepository.findByStudentIdInAndMonth(studentIds, month);
    }
    
    public List<Marks> getMarksByStudent(Long studentId) {
        return marksRepository.findByStudentId(studentId);
    }
    public void deleteAllMarks() {
        marksRepository.deleteAll();
    }
    
    public List<Marks> getStudentMarks(Long studentId, String month) {
        return marksRepository.findByStudentIdAndMonth(studentId, month);
    }

    public void saveOrUpdateMarks(Marks marks) {

        Optional<Marks> existing =
                marksRepository.findByStudentIdAndSubjectAndMonth(
                        marks.getStudentId(),
                        marks.getSubject(),
                        marks.getMonth());

        if(existing.isPresent()) {

            Marks old = existing.get();

            old.setExamMarks(marks.getExamMarks());
            old.setAssignmentMarks(marks.getAssignmentMarks());

            marksRepository.save(old);

        } else {

            marksRepository.save(marks);

        }

    }
    
}