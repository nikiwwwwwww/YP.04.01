package com.example.itogproject.controllers;

import com.example.itogproject.models.PostEmploye;
import com.example.itogproject.models.Salary;
import com.example.itogproject.models.Stake;
import com.example.itogproject.repositories.PostEmployeRepository;
import com.example.itogproject.repositories.SallaryRepository;
import com.example.itogproject.repositories.StakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.List;

@Controller
@PreAuthorize("hasAnyAuthority('BUGHALTER')")
public class BughalterController {

    @Controller
    public class StakeController {

        @Autowired
        private StakeRepository stakeRepository;



        @GetMapping("/menuBughalter")
        public String menuBughalter(Model model) {
            return "menuBughalter";
        }

        @GetMapping("/stakePage")
        public String stakePage(@RequestParam(name = "search", required = false) Integer search, Model model) {
            List<Stake> stakes;
            if (search!=null) {
                stakes = stakeRepository.findAllByCountDaysWorked(search);
            }
            else{
                stakes = stakeRepository.findAll();
            }

            model.addAttribute("stakes", stakes);
            return "stakePage";
        }

        @PostMapping("/addStake")
        public String addStake(@RequestParam("countDaysWorked") int countDaysWorked,
                               @RequestParam("paymentPerDay") float paymentPerDay,
                               @RequestParam("dateOfSalary") Date dateOfSalary,
                               @RequestParam("paymentPerShift") float paymentPerShift) {
            long id = 0;
            Stake stake = new Stake(id, countDaysWorked, paymentPerDay, dateOfSalary, paymentPerShift);
            stakeRepository.save(stake);
            return "redirect:/stakePage";
        }

        @GetMapping("/editStake/{id}")
        public String editStakeForm(@PathVariable long id, Model model) {
            Stake stake = stakeRepository.findById(id).orElse(null);
            model.addAttribute("stake", stake);
            return "editStake";
        }

        @PostMapping("/editStake/{id}")
        public String editStake(@PathVariable long id,
                                @RequestParam("countDaysWorked") int countDaysWorked,
                                @RequestParam("paymentPerDay") float paymentPerDay,
                                @RequestParam("dateOfSalary") Date dateOfSalary,
                                @RequestParam("paymentPerShift") float paymentPerShift) {
            Stake stakeToBeUpdated = stakeRepository.findById(id).orElse(null);
            if (stakeToBeUpdated != null) {
                stakeToBeUpdated.setCountDaysWorked(countDaysWorked);
                stakeToBeUpdated.setPaymentPerDay(paymentPerDay);
                stakeToBeUpdated.setDateOfSalary(dateOfSalary);
                stakeToBeUpdated.setPaymentPerShift(paymentPerShift);
                stakeRepository.save(stakeToBeUpdated);
            }
            return "redirect:/stakePage";
        }

        @PostMapping("/deleteStake/{id}")
        public String deleteStake(@PathVariable long id) {
            stakeRepository.deleteById(id);
            return "redirect:/stakePage";
        }
    }

    @Autowired
    private SallaryRepository sallaryRepository;

    @Autowired
    private PostEmployeRepository postEmployeRepository;

    @Autowired
    private StakeRepository stakeRepository1;

    @GetMapping("/salaryPage")
    public String salaryPage(@RequestParam(name = "search", required = false) String search, Model model) {
        List<PostEmploye> postEmployes;
        List<Stake> stakes;
        List<Salary> salaries;
        if (StringUtils.hasText(search)) {
            salaries = sallaryRepository.findAllByPostEmployeEmployeUsername(search);
        }
        else{
            salaries = sallaryRepository.findAll();
        }
        stakes = stakeRepository1.findAll();
        postEmployes = postEmployeRepository.findAll();
        model.addAttribute("salaries", salaries);
        model.addAttribute("stakes", stakes);
        model.addAttribute("postEmployes", postEmployes);
        return "salaryPage";
    }

    @PostMapping("/addSalary")
    public String addSalary(@RequestParam("postEmployeId") long postEmployeId,
                            @RequestParam("stakeId") long stakeId) {
        PostEmploye postEmploye = postEmployeRepository.findById(postEmployeId).orElse(null);
        Stake stake = stakeRepository1.findById(stakeId).orElse(null);

        if (postEmploye != null && stake != null) {
            Salary salary = new Salary(null, postEmploye, stake);
            sallaryRepository.save(salary);
        }

        return "redirect:/salaryPage";
    }

    @GetMapping("/editSalary/{id}")
    public String editSalaryForm(@PathVariable long id, Model model) {
        Salary salary = sallaryRepository.findById(id).orElse(null);
        List<PostEmploye> postEmployes = postEmployeRepository.findAll();
        List<Stake> stakes = stakeRepository1.findAll();

        model.addAttribute("salary", salary);
        model.addAttribute("postEmployes", postEmployes);
        model.addAttribute("stakes", stakes);

        return "editSalary";
    }

    @PostMapping("/editSalary/{id}")
    public String editSalary(@PathVariable long id,
                             @RequestParam("postEmployeId") long postEmployeId,
                             @RequestParam("stakeId") long stakeId) {
        Salary salaryToBeUpdated = sallaryRepository.findById(id).orElse(null);
        PostEmploye postEmploye = postEmployeRepository.findById(postEmployeId).orElse(null);
        Stake stake = stakeRepository1.findById(stakeId).orElse(null);

        if (salaryToBeUpdated != null && postEmploye != null && stake != null) {
            salaryToBeUpdated.setPostEmploye(postEmploye);
            salaryToBeUpdated.setStake(stake);
            sallaryRepository.save(salaryToBeUpdated);
        }

        return "redirect:/salaryPage";
    }

    @PostMapping("/deleteSalary/{id}")
    public String deleteSalary(@PathVariable long id) {
        sallaryRepository.deleteById(id);
        return "redirect:/salaryPage";
    }

}
