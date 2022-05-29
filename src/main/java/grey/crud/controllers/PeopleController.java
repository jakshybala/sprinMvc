package grey.crud.controllers;

import grey.crud.dao.PersonDao;
import grey.crud.model.Person;
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
    private final PersonDao personDao;

    public PeopleController(PersonDao personDao) {
        this.personDao = personDao;
    }
    @GetMapping()
    public String indexAllPeople(Model model) {
        model.addAttribute("allPeople", personDao.indexOfAllPeople());
        return "people/indexallpeople";   }
    @GetMapping("/{id}")
    public String showPeopleById(@PathVariable("id") int id, Model model) throws Exception {
        model.addAttribute("personById", personDao.showById(id));
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
        if(bindingResult.hasErrors()) {
            return "people/save";
        }
        personDao.save(person);
        return "redirect:/people";

    }
    @GetMapping("/{id}/edit")
    public String editMetod(Model model, @PathVariable("id") int id) throws Exception {
        model.addAttribute("personeditById", personDao.showById(id));
        return "people/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("personeditById")
                             @Valid Person person, BindingResult bindingResult,
                         @PathVariable("id") int id) throws Exception {
        if(bindingResult.hasErrors()) {
            return "people/edit";
        }
        personDao.update(id, person);
        return "redirect:/people";
    }
    @DeleteMapping("/{id}")
    public String deletPerson(@PathVariable("id") int id) {
        personDao.delete(id);
        return "redirect:/people";

    }


    }
