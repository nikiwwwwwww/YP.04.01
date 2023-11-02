package com.example.itogproject.controllers;

import com.example.itogproject.models.EducationEmploye;
import com.example.itogproject.models.Employe;
import com.example.itogproject.models.EmployeTasks;
import com.example.itogproject.models.Tasks;
import com.example.itogproject.repositories.EmployeRepository;
import com.example.itogproject.repositories.EmployeTasksRepository;
import com.example.itogproject.repositories.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;


@Controller
@PreAuthorize("hasAnyAuthority('RUKOVOD')")
public class RukovodController {

    @Autowired
    private TasksRepository tasksRepository;

    @GetMapping("/menuRukovod")
    public String menuRukovod(Model model) {
        return "menuRukovod";
    }


    @GetMapping("/tasksPage")
    public String tasksPage(@RequestParam(name = "search", required = false) String search, Model model) {
        List<Tasks> tasks;
        if (StringUtils.hasText(search)) {
            tasks = tasksRepository.findAllByTopic(search);
        }
        else {
            tasks = tasksRepository.findAll();
        }

        model.addAttribute("tasks", tasks);
        return "tasksPage";
    }

    @PostMapping("/addTasks")
    public String addTasks(@RequestParam("topic") String topic,
                           @RequestParam("description") String description,
                           @RequestParam("complexity") String complexity,
                           @RequestParam("dateOfIssue") String dateOfIssue,
                           @RequestParam("dateOfEnd") String dateOfEnd) throws ParseException {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        final SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");

        Tasks task = new Tasks(null, topic, description, complexity, dateFormat.parse(dateOfIssue), dateFormat2.parse(dateOfEnd));
        tasksRepository.save(task);
        return "redirect:/tasksPage";
    }

    @GetMapping("/editTasks/{id}")
    public String editTasksForm(@PathVariable long id, Model model) {
        Tasks task = tasksRepository.findById(id).orElse(null);
        model.addAttribute("task", task);
        return "editTasks";
    }

    @PostMapping("/editTasks/{id}")
    public String editTasks(@PathVariable long id,
                            @RequestParam("topic") String topic,
                            @RequestParam("description") String description,
                            @RequestParam("complexity") String complexity,
                            @RequestParam("dateOfIssue") String dateOfIssue,
                            @RequestParam("dateOfEnd") String dateOfEnd) throws ParseException {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        final SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        Tasks taskToBeUpdated = tasksRepository.findById(id).orElse(null);
        if (taskToBeUpdated != null) {
            taskToBeUpdated.setTopic(topic);
            taskToBeUpdated.setDescription(description);
            taskToBeUpdated.setComplexity(complexity);
            taskToBeUpdated.setDateOfIssue(dateFormat.parse(dateOfIssue));
            taskToBeUpdated.setDateOfEnd(dateFormat2.parse(dateOfEnd));
            tasksRepository.save(taskToBeUpdated);
        }
        return "redirect:/tasksPage";
    }

    @PostMapping("/deleteTasks/{id}")
    public String deleteTasks(@PathVariable long id) {
        tasksRepository.deleteById(id);
        return "redirect:/tasksPage";
    }

    @Autowired
    private EmployeTasksRepository employeTasksRepository;

    @Autowired
    private EmployeRepository employeRepository;

    @GetMapping("/employeTasksPage")
    public String employeTasksPage(@RequestParam(name = "search", required = false) String search, Model model) {
        List<EmployeTasks> employeTasks ;
        if (StringUtils.hasText(search)) {
            employeTasks = employeTasksRepository.findAllByEmployeUsername(search);
        }
        else {
            employeTasks = employeTasksRepository.findAll();
        }

        model.addAttribute("employeTasks", employeTasks);
        List<Employe> employe = employeRepository.findAll();
        List<Tasks> tasks = tasksRepository.findAll();
        model.addAttribute("employe", employe);
        model.addAttribute("tasks", tasks);
        return "employeTasksPage";
    }

    @PostMapping("/addEmployeTasks")
    public String assignTasks(@RequestParam("taskId") long taskId,@RequestParam("employeId") long employeId) {
        Employe employe = employeRepository.findById(employeId).orElse(null);
        Tasks task = tasksRepository.findById(taskId).orElse(null);

        if (employe != null && task != null) {
            EmployeTasks employeTasks = new EmployeTasks(null, task, employe);
            employeTasksRepository.save(employeTasks);
        }
        return "redirect:/employeTasksPage";
    }

    @PostMapping("/deleteEmployeTasks/{id}")
    public String deleteEmployeTasks(@PathVariable long id) {
        employeTasksRepository.deleteById(id);
        return "redirect:/employeTasksPage";
    }

    @GetMapping("/editEmployeTasks/{id}")
    public String editEmployeTasksForm(@PathVariable long id, Model model) {
        List<Employe> employes = employeRepository.findAll();
        List<Tasks> tasks = tasksRepository.findAll();
        model.addAttribute("employess", employes);
        model.addAttribute("taskss", tasks);
        EmployeTasks employeTasks = employeTasksRepository.findById(id).orElse(null);
        model.addAttribute("employeTasks", employeTasks);
        return "editEmployeTasks";
    }

    @PostMapping("/editEmployeTasks/{id}")
    public String editEmployeTasks(@PathVariable Long id, @ModelAttribute EmployeTasks updatedEmployeTasks) {
        EmployeTasks employeTasksToBeUpdated = employeTasksRepository.findById(id).orElse(null);
        if (employeTasksToBeUpdated != null) {
            employeTasksToBeUpdated.setTasks(updatedEmployeTasks.getTasks());
            employeTasksToBeUpdated.setEmploye(updatedEmployeTasks.getEmploye());

            employeTasksRepository.save(employeTasksToBeUpdated);
        }
        return "redirect:/employeTasksPage";
    }



}
