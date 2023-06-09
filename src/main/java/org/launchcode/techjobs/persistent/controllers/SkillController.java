package org.launchcode.techjobs.persistent.controllers;

import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("skills")
public class SkillController {
        @Autowired
        private SkillRepository skillRepository;
    @RequestMapping("")
        @GetMapping
        public String index(Model model) {
            model.addAttribute("skill", skillRepository.findAll());
            return "skills/index";
        }
        @GetMapping("add")
        public String displayAddSkillForm(Model model) {
            model.addAttribute(new Skill());
            return "skills/add";
        }
        // Contollers #3
        @PostMapping("add")
        public String processAddSkillForm(@ModelAttribute @Valid Skill newSkill,
                                             Errors errors, Model model) {

            if (errors.hasErrors()) {
                return "skills/add";
            }
            //Controllers#3
            skillRepository.save(newSkill);
            return "redirect:";
        }
        //Controllers #4 - displayViewEmployer will be in charge of rendering a page to view the contents of an individual employer object. It will make use of the employer object's id field to grab the correct information from employerRepository. optEmployer is currently initialized to null.
        @GetMapping("view/{skillId}")
        public String displayViewSkill(Model model, @PathVariable int skillId) {

            Optional optSkill = skillRepository.findById(skillId);
            if (optSkill.isPresent()) {
                Skill skill = (Skill) optSkill.get();
                model.addAttribute("skill", skill);
                return "skills/view";
            } else {
                return "redirect:../";
            }
        }
    }

