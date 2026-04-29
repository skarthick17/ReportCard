package com.example.reportcard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.reportcard.model.User;
import com.example.reportcard.service.UserService;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model) {

        User user = userService.login(username, password);

        if(user == null) {
            model.addAttribute("error", "Invalid Username or Password");
            return "login";
        }

        if(user.getRole().equals("PARENT")) {
            return "redirect:/parent/dashboard";
        }

        if(user.getRole().equals("TEACHER")) {
            return "redirect:/teacher/dashboard";
        }

        if(user.getRole().equals("PRINCIPAL")) {
            return "redirect:/principal/dashboard";
        }

        return "login";
    }
}