package com.example.itogproject.controllers;


import com.example.itogproject.models.*;
import com.example.itogproject.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class AdminController {

    @Autowired
    private EducationRepository educationRepository;

    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private EducationEmployeRepository educationEmployeRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostEmployeRepository postEmployeRepository;

    @GetMapping("/menuAdmin")
    public String adminPage() {
        return "menuAdmin";
    }

    @GetMapping("/educationPage")
    public String educationPage(@RequestParam(name = "search", required = false) String search, Model model) {
        List<Education> educations;

        if (StringUtils.hasText(search)) {
            educations = educationRepository.findAllBySpecialization(search);
        } else {
            educations = educationRepository.findAll();
        }


        model.addAttribute("educations", educations);
        return "educationPage";
    }

    @PostMapping("/addEducation")
    public String addEducation(@RequestParam("typeOfEducation") String typeOfEducation,
                             @RequestParam("specialization") String specialization,
                             @RequestParam("institution") String institution) {
        long id = 0;
        Education education = new Education(id, typeOfEducation, specialization, institution);
        educationRepository.save(education);
        return "redirect:/educationPage";
    }
    @GetMapping("/editEducation/{id}")
    public String editEducationForm(@PathVariable long id, Model model) {
        Education education = educationRepository.findById(id).orElse(null);
        model.addAttribute("education", education);
        return "editEducation";
    }
    @PostMapping("/editEducation/{id}")
    public String editEducation(@PathVariable long id, @ModelAttribute Education updatedEducation) {
        Education educationToBeUpdated = educationRepository.findById(id).orElse(null);
        if (updatedEducation != null) {
            educationToBeUpdated.setTypeOfEducation(updatedEducation.getTypeOfEducation());
            educationToBeUpdated.setSpecialization(updatedEducation.getSpecialization());
            educationToBeUpdated.setInstitution(updatedEducation.getInstitution());
            educationRepository.save(educationToBeUpdated);
        }
        return "redirect:/educationPage";
    }

    @PostMapping("/deleteEducation/{id}")
    public String deleteProduct(@PathVariable long id) {
        educationRepository.deleteById(id);
        return "redirect:/educationPage";
    }


    @GetMapping("/employePage")
    public String employePage(@RequestParam(name = "search", required = false) String search, Model model) {
        List<Employe> employes;
        if (StringUtils.hasText(search)) {
            employes = employeRepository.findAllByUsername(search);
        }
        else {
            employes = employeRepository.findAll();
        }
        model.addAttribute("employes", employes);
        return "employePage";
    }

    @PostMapping("/addEmploye")
    public String addEmploye(Model model, @RequestParam("name") String name, @RequestParam("lastName") String lastName, @RequestParam("middleName") String middleName, @RequestParam("dateOfEmployment") String dateOfEmployment, @RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("phoneNumber") String phoneNumber) throws ParseException {
        long id = 0;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Employe employe = new Employe(id, name, lastName, middleName,formatter.parse(dateOfEmployment),username, password, phoneNumber);
        employeRepository.save(employe);
        return "redirect:/employePage";
    }

    @GetMapping("/editEmploye/{id}")
    public String editEmployeForm(@PathVariable long id, Model model) {
        Employe employe = employeRepository.findById(id).orElse(null);
        model.addAttribute("employe", employe);
        return "editEmploye";
    }

    @PostMapping("/editEmploye/{id}")
    public String editEmploye(@PathVariable long id, @ModelAttribute editEmployeClass updatedEmploye, BindingResult bindingResult, Model model) throws ParseException {

        Employe employeToBeUpdated = employeRepository.findById(id).orElse(null);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        if (updatedEmploye != null) {
            employeToBeUpdated.setName(updatedEmploye.getName());
            employeToBeUpdated.setLastName(updatedEmploye.getLastName());
            employeToBeUpdated.setMiddleName(updatedEmploye.getMiddleName());
            employeToBeUpdated.setDateOfEmployment(formatter.parse(updatedEmploye.getDateOfEmployment()));
            employeToBeUpdated.setUsername(updatedEmploye.getUsername());
            employeToBeUpdated.setPassword(updatedEmploye.getPassword());
            employeToBeUpdated.setPhoneNumber(updatedEmploye.getPhoneNumber());

            employeRepository.save(employeToBeUpdated);
        }
        return "redirect:/employePage";
    }

    @PostMapping("/deleteEmploye/{id}")
    public String deleteEmploye(@PathVariable long id) {
        employeRepository.deleteById(id);
        return "redirect:/employePage";
    }

    @GetMapping("/educationEmploye")
    public String educationEmploye(@RequestParam(name = "search", required = false) String search, Model model) {
        List<EducationEmploye> educationEmployeList;
        List<Employe> employeList;
        List<Education> educationList;
        if (StringUtils.hasText(search)) {
            educationEmployeList = educationEmployeRepository.findAllByEmploye_Username(search);
        } else {
            educationEmployeList = educationEmployeRepository.findAll();
        }

        employeList = employeRepository.findAll();
        educationList = educationRepository.findAll();
        model.addAttribute("educationEmployeList", educationEmployeList);
        model.addAttribute("employe", employeList);
        model.addAttribute("education", educationList);
        return "educationEmploye";
    }

    @PostMapping("/addEducationEmploye")
    public String addEducationEmploye(@RequestParam long educationId, @RequestParam long employeId) {
        Employe employe = employeRepository.getById(employeId);
        Education education = educationRepository.getById(educationId);

        EducationEmploye educationEmploye = new EducationEmploye();
        educationEmploye.setEducation(education);
        educationEmploye.setEmploye(employe);

        educationEmployeRepository.save(educationEmploye);
        return "redirect:/educationEmploye";
    }

    @GetMapping("/editEducationEmploye/{id}")
    public String editEducationEmployeForm(@PathVariable Long id, Model model) {
        EducationEmploye educationEmploye = educationEmployeRepository.findById(id).orElse(null);
        model.addAttribute("educationEmploye", educationEmploye);
        List<Employe> employeList = employeRepository.findAll();
        List<Education> educationList = educationRepository.findAll();
        model.addAttribute("employe", employeList);
        model.addAttribute("education", educationList);
        return "editEducationEmploye";
    }

    @PostMapping("/editEducationEmploye/{id}")
    public String editEducationEmploye(@PathVariable Long id, @ModelAttribute EducationEmploye updatedEducationEmploye) {
        EducationEmploye educationEmployeToBeUpdated = educationEmployeRepository.findById(id).orElse(null);
        if (educationEmployeToBeUpdated != null) {
            // Update fields based on your requirements
            educationEmployeToBeUpdated.setEducation(updatedEducationEmploye.getEducation());
            educationEmployeToBeUpdated.setEmploye(updatedEducationEmploye.getEmploye());

            educationEmployeRepository.save(educationEmployeToBeUpdated);
        }
        return "redirect:/educationEmploye";
    }

    @GetMapping("/deleteEducationEmploye/{id}")
    public String deleteEducationEmploye(@PathVariable Long id) {
        educationEmployeRepository.deleteById(id);
        return "redirect:/educationEmploye";
    }




    @GetMapping("/postPage")
    public String postPage(@RequestParam(name = "search", required = false) String search, Model model) {
        List<Post> posts;
        if (StringUtils.hasText(search)) {
            posts = postRepository.findAllByNamePost(search);
        }
        else {
            posts = postRepository.findAll();
        }

        model.addAttribute("posts", posts);
        return "postPage";
    }

    @PostMapping("/addPost")
    public String addPost(@RequestParam("namePost") String namePost, BindingResult bindingResult,Model model) {

        Post post = new Post();
        post.setNamePost(namePost);
        postRepository.save(post);
        return "redirect:/postPage";
    }

    @GetMapping("/editPost/{id}")
    public String editPostForm(@PathVariable long id, Model model) {
        Post post = postRepository.findById(id).orElse(null);
        model.addAttribute("post", post);
        return "editPost";
    }

    @PostMapping("/editPost/{id}")
    public String editPost(@PathVariable long id, @ModelAttribute Post updatedPost) {
        Post postToBeUpdated = postRepository.findById(id).orElse(null);
        if (postToBeUpdated != null && updatedPost != null) {
            postToBeUpdated.setNamePost(updatedPost.getNamePost());
            postRepository.save(postToBeUpdated);
        }
        return "redirect:/postPage";
    }

    @PostMapping("/deletePost/{id}")
    public String deletePost(@PathVariable long id) {
        postRepository.deleteById(id);
        return "redirect:/postPage";
    }

    @GetMapping("/postEmployePage")
    public String postEmployePage(@RequestParam(name = "search", required = false) String search, Model model) {
        List<PostEmploye> postEmployes ;
        if (StringUtils.hasText(search)) {
            postEmployes = postEmployeRepository.findAllByEmployeUsername(search);

        }
        else {
            postEmployes = postEmployeRepository.findAll();

        }
        model.addAttribute("postEmployes", postEmployes);
        List<Employe> employes = employeRepository.findAll();
        model.addAttribute("employes", employes);
        List<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);

        return "postEmployePage";
    }

    @PostMapping("/addPostEmploye")
    public String addPostEmploye(@RequestParam("postId") Long postId,
                                 @RequestParam("employeId") Long employeId) {
        Post post = postRepository.findById(postId).orElse(null);
        Employe employe = employeRepository.findById(employeId).orElse(null);

        if (post != null && employe != null) {
            PostEmploye postEmploye = new PostEmploye();
            postEmploye.setPost(post);
            postEmploye.setEmploye(employe);
            postEmployeRepository.save(postEmploye);
        }

        return "redirect:/postEmployePage";
    }

    @GetMapping("/editPostEmploye/{id}")
    public String editPostEmployeForm(@PathVariable long id, Model model) {
        PostEmploye postEmploye = postEmployeRepository.findById(id).orElse(null);
        model.addAttribute("postEmploye", postEmploye);
        model.addAttribute("posts", postRepository.findAll());
        model.addAttribute("employes", employeRepository.findAll());
        return "editPostEmploye";
    }

    @PostMapping("/editPostEmploye/{id}")
    public String editPostEmploye(@PathVariable long id,
                                  @RequestParam("postId") Long postId,
                                  @RequestParam("employeId") Long employeId) {
        PostEmploye postEmployeToBeUpdated = postEmployeRepository.findById(id).orElse(null);
        Post post = postRepository.findById(postId).orElse(null);
        Employe employe = employeRepository.findById(employeId).orElse(null);

        if (postEmployeToBeUpdated != null && post != null && employe != null) {
            postEmployeToBeUpdated.setPost(post);
            postEmployeToBeUpdated.setEmploye(employe);
            postEmployeRepository.save(postEmployeToBeUpdated);
        }

        return "redirect:/postEmployePage";
    }

    @PostMapping("/deletePostEmploye/{id}")
    public String deletePostEmploye(@PathVariable long id) {
        postEmployeRepository.deleteById(id);
        return "redirect:/postEmployePage";
    }

}
