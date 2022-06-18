package grey.crud.controllers;

/*
grey.crud.controllers
Tarih: 30.05.2022, Saat: 18:01, Author: Grey 
*/

import grey.crud.model.Company;
import grey.crud.services.CompanyService;
import grey.crud.services.CourseService;
import grey.crud.util.CompanyValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/company")
public class CompanyController {
    private final CompanyValidator companyValidator;
    private final CompanyService companyService;
    @Autowired
    public CompanyController(CompanyValidator companyValidator, CompanyService companyService, CourseService courseService) {
        this.companyValidator = companyValidator;
        this.companyService = companyService;
    }

    @GetMapping()
    public String allCompanies(Model model){
        model.addAttribute("allCompany", companyService.showAll());
        return "company/all";
    }
    @GetMapping("/{id}")
    public String showById(@PathVariable("id") int id, Model model) {
        model.addAttribute("showbyid", companyService.getById(id));
        return "company/id";
    }
    @GetMapping("/new")
    public String createCompany(Model model) {
        model.addAttribute("createcomapny", new Company());
        return "company/save";
    }
    @PostMapping()
    public String saveCompany(@ModelAttribute("createcomapny")
                              @Valid Company company, BindingResult bindingResult) {
        companyValidator.validate(company, bindingResult);
        if (bindingResult.hasErrors()){
            return "company/save";
        }
        companyService.saveCompany(company);
        return "redirect:/api/company";

    }
    @GetMapping("/{id}/edit")
    public String editCompany(Model model, @PathVariable("id") int id) {
        model.addAttribute("updateCompany", companyService.getById(id));
        return "company/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("updateCompany")
                         @Valid Company company, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        companyValidator.validate(company, bindingResult);
        if (bindingResult.hasErrors()) {
            return "company/edit";
        }
        companyService.updateCompany(id, company);
        return "redirect:/api/company";
    }
    @DeleteMapping("/{id}")
    public String deletCompany(@PathVariable("id") int id) {
        companyService.deletCompany(id);
        return "redirect:/api/company";
    }







}
