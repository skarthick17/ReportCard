package com.example.reportcard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.reportcard.model.Student;
import com.example.reportcard.model.Marks;
import com.example.reportcard.service.MarksService;
import com.example.reportcard.service.StudentService;

@Controller
public class ParentController {

    @Autowired
    private MarksService marksService;
    @Autowired
    private StudentService studentService;

    @GetMapping("/parent/dashboard")
    public String parentDashboard() {
        return "parent/parent-dashboard";
    }

    @PostMapping("/parent/view-result")
    public String viewResult(@RequestParam Long studentId,
                             @RequestParam String month,
                             Model model) {

        Student student = studentService.getStudentById(studentId);

        List<Marks> marks = marksService.getStudentMarks(studentId, month);

        model.addAttribute("student", student);

        if(marks.isEmpty()) {
            model.addAttribute("message", "No exam in this month");
        } else {
            model.addAttribute("marks", marks);
        }

        return "parent/parent-dashboard";
    }

}