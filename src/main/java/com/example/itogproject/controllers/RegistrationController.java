package com.example.itogproject.controllers;


import com.example.itogproject.models.Employe;
import com.example.itogproject.models.Post;
import com.example.itogproject.models.PostEmploye;
import com.example.itogproject.models.modelUser;
import com.example.itogproject.repositories.EmployeRepository;
import com.example.itogproject.repositories.PostEmployeRepository;
import com.example.itogproject.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.xml.crypto.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;

@Controller
public class RegistrationController {
    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostEmployeRepository postEmployeRepository;

    @GetMapping("/registration")
    private String registrationView() {
        return "registrationPage";
    }
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    @PostMapping("/registration")
    private String registration(modelUser modeluser, Model model) {
        Employe userFromDb = employeRepository.findByUsername(modeluser.getUsername());
        if (userFromDb != null) {
            model.addAttribute("message", "Пользователь с таким логином уже существует");
            return "registrationPage";
        }
        Employe employe = new Employe();
        try {
            employe.setDateOfEmployment(dateFormat.parse(modeluser.getDateOfEmployment()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        employe.setPassword(modeluser.getPassword());
        employe.setPhoneNumber(modeluser.getPhoneNumber());
        employe.setMiddleName(modeluser.getMiddleName());
        employe.setName(modeluser.getName());
        employe.setLastName(modeluser.getLastName());
        employe.setPhoneNumber(modeluser.getPhoneNumber());
        employe.setUsername(modeluser.getUsername());
        employe.setId(modeluser.getId());
        employeRepository.save(employe);

        Post managerPost = postRepository.findByNamePost("USER");
        PostEmploye postEmploye = new PostEmploye();
        postEmploye.setPost(managerPost);
        postEmploye.setEmploye(employe);
        postEmployeRepository.save(postEmploye);

        return "redirect:/login";
    }
}

