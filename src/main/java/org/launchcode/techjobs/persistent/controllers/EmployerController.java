package org.launchcode.techjobs.persistent.controllers;

import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@Controller
@RequestMapping("employers")
public class EmployerController {
    @Autowired
    private EmployerRepository employerRepository;
    @RequestMapping("")
    public String index(Model model) {
            model.addAttribute( "employers", employerRepository.findAll());
       return "employers/index";
    }
    @GetMapping("add")
    public String displayAddEmployerForm(Model model) {
        model.addAttribute(new Employer());
        return "employers/add";
    }
// Contollers #3
    @PostMapping("add")
    public String processAddEmployerForm(@ModelAttribute @Valid Employer employer,
                                    Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "employers/add";
        }
        //Controllers#3
        employerRepository.save(employer);
        return "redirect:";
    }
//Controllers #4 - displayViewEmployer will be in charge of rendering a page to view the contents of an individual employer object. It will make use of the employer object's id field to grab the correct information from employerRepository. optEmployer is currently initialized to null.
    @GetMapping("view/{employerId}")
    public String displayViewEmployer(Model model, @PathVariable int employerId) {

        Optional optEmployer = employerRepository.findById(employerId);//null;
        if (optEmployer.isPresent()) {
            Employer employer = (Employer) optEmployer.get();
            model.addAttribute("employer", employerId);
            return "employers/view";
        } else {
            return "redirect:../";
        }
    }
}
