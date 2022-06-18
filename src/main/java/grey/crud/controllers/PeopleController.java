package grey.crud.controllers;

import grey.crud.model.Person;
import grey.crud.services.PersonService;
import grey.crud.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/*
grey.crud.controllers
Tarih: 28.05.2022, Saat: 3:46, Author: Grey 
*/
@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonService personService;
    private final PersonValidator personValidator;
    @Autowired
    public PeopleController(PersonService personService, PersonValidator personValidator) {
        this.personService = personService;
        this.personValidator = personValidator;
    }
    @GetMapping()
    public String indexAllPeople(Model model) {
        model.addAttribute("allPeople", personService.showAll());
        return "people/indexallpeople";   }

    @GetMapping("/{id}")
    public String showPeopleById(@PathVariable("id") int id, Model model) throws Exception {
        model.addAttribute("personById", personService.getById(id));
        return "people/showbyid";
    }
    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("savenewperson", new Person());
        return "people/save";
    }
    @PostMapping()
    public String createPerson(@ModelAttribute("savenewperson")
                                   @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if(bindingResult.hasErrors()) {
            return "people/save";
        }
        personService.savePerson(person);
        return "redirect:/people";

    }
    @GetMapping("/{id}/edit")
    public String editMetod(Model model, @PathVariable("id") int id) throws Exception {
        model.addAttribute("personeditById", personService.getById(id));
        return "people/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("personeditById")
                             @Valid Person person, BindingResult bindingResult,
                         @PathVariable("id") int id) throws Exception {
        personValidator.validate(person, bindingResult);
        if(bindingResult.hasErrors()) {
            return "people/edit";
        }
        personService.updatePerson(id, person);
        return "redirect:/people";
    }
    @DeleteMapping("/{id}")
    public String deletPerson(@PathVariable("id") int id) {
        personService.deletPerson(id);
        return "redirect:/people";

    }


    }
