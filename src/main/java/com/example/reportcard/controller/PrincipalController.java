package com.example.reportcard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import com.example.reportcard.model.Student;
import com.example.reportcard.repository.MarksRepository;
import com.example.reportcard.repository.StudentRepository;
import com.example.reportcard.model.Marks;
import com.example.reportcard.service.StudentService;
import com.example.reportcard.service.MarksService;

@Controller
public class PrincipalController {

	@PersistenceContext
	private EntityManager entityManager;
	
    @Autowired
    private StudentService studentService;

    @Autowired
    private MarksService marksService;
    
    @Autowired
    private MarksRepository marksRepository;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/principal/dashboard")
    public String principalDashboard() {
        return "principal/principal-dashboard";
    }

    @PostMapping("/principal/view-class")
    public String viewClass(@RequestParam String studentClass,
                            @RequestParam String section,
                            Model model) {

        List<Student> students = studentService.getStudents(studentClass, section);

        model.addAttribute("students", students);

        return "principal/principal-dashboard";
    }

    @PostMapping("/principal/view-marks")
    public String viewMarks(@RequestParam Long studentId, Model model) {

        List<Marks> marks = marksService.getMarksByStudent(studentId);

        model.addAttribute("marks", marks);

        return "principal/principal-dashboard";
    }

    @Transactional
    @PostMapping("/principal/delete-all")
    public String deleteAll(Model model) {

        marksRepository.truncateMarks();
        studentRepository.truncateStudents();

        model.addAttribute("message", "School database reset successfully");

        return "principal/principal-dashboard";
    }
    @GetMapping("/principal/class/{studentClass}")
    public String viewSections(@PathVariable String studentClass, Model model) {

        model.addAttribute("studentClass", studentClass);

        return "principal/sections";
    }
    @GetMapping("/principal/class/{studentClass}/{section}")
    public String viewStudents(@PathVariable String studentClass,
                               @PathVariable String section,
                               Model model) {

        List<Student> students = studentService.getStudents(studentClass, section);

        model.addAttribute("students", students);
        model.addAttribute("studentClass", studentClass);
        model.addAttribute("section", section);

        return "principal/view-students";
    }
    @PostMapping("/principal/delete-class")
    public String deleteClass(@RequestParam String studentClass,
                              @RequestParam String section,
                              Model model) {

        studentService.deleteClass(studentClass, section);

        model.addAttribute("message", "Class data deleted successfully");

        return "principal/principal-dashboard";
    }
}