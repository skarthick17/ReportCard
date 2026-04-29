package com.example.reportcard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.reportcard.model.Student;
import com.example.reportcard.model.Marks;
import com.example.reportcard.service.StudentService;
import com.example.reportcard.service.MarksService;

@Controller
public class TeacherController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private MarksService marksService;

    @GetMapping("/teacher/dashboard")
    public String teacherDashboard() {
        return "teacher/teacher-dashboard";
    }

    @GetMapping("/teacher/new-class")
    public String newClass() {
        return "teacher/new-class";
    }

    @PostMapping("/teacher/save-class")
    public String saveClass(@RequestParam String teacherName,
                            @RequestParam String studentClass,
                            @RequestParam String section,
                            @RequestParam List<String> studentNames,
                            Model model) {

        for(String name : studentNames) {

            Student student = new Student();
            student.setName(name);
            student.setStudentClass(studentClass);
            student.setSection(section);

            studentService.saveStudent(student);
        }

        model.addAttribute("message", "Class created successfully");

        return "teacher/teacher-dashboard";
    }
    @GetMapping("/teacher/existing-class")
    public String existingClass() {
        return "teacher/existing-class";
    }

    @PostMapping("/teacher/show-students")
    public String showStudents(@RequestParam String studentClass,
                               @RequestParam String section,
                               Model model) {

        List<Student> students = studentService.getStudents(studentClass, section);

        model.addAttribute("students", students);
        model.addAttribute("studentClass", studentClass);
        model.addAttribute("section", section);

        return "teacher/enter-marks";
    }

    @PostMapping("/teacher/save-marks")
    public String saveMarks(@RequestParam Long studentId,
                            @RequestParam String subject,
                            @RequestParam int examMarks,
                            @RequestParam int assignmentMarks,
                            @RequestParam String month,
                            @RequestParam String studentClass,
                            @RequestParam String section) {

        Marks marks = new Marks();

        marks.setStudentId(studentId);
        marks.setSubject(subject);
        marks.setExamMarks(examMarks);
        marks.setAssignmentMarks(assignmentMarks);
        marks.setMonth(month);

        marksService.saveOrUpdateMarks(marks);

        return "redirect:/teacher/load-class?studentClass=" + studentClass + "&section=" + section;
    }
    @GetMapping("/teacher/load-class")
    public String loadClass(@RequestParam String studentClass,
                            @RequestParam String section,
                            Model model) {

        List<Student> students = studentService.getStudents(studentClass, section);

        model.addAttribute("students", students);
        model.addAttribute("studentClass", studentClass);
        model.addAttribute("section", section);

        return "teacher/enter-marks";
    }
    @GetMapping("/teacher/view-class")
    public String viewClassPage() {
        return "teacher/view-class";
    }
    @PostMapping("/teacher/view-class-result")
    public String viewClassMarks(@RequestParam String studentClass,
                                 @RequestParam String section,
                                 @RequestParam String month,
                                 Model model) {

        List<Student> students = studentService.getStudents(studentClass, section);

        List<Long> studentIds = students.stream()
                                        .map(Student::getId)
                                        .toList();

        List<Marks> marks = marksService.getMarksByStudentsAndMonth(studentIds, month);

        if(marks.isEmpty()){
            model.addAttribute("message","No exam in this month");
        }

        model.addAttribute("marks", marks);
        model.addAttribute("students", students);   // IMPORTANT

        return "teacher/view-class-result";
    }}