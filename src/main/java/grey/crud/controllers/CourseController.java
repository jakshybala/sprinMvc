package grey.crud.controllers;

import grey.crud.model.Company;
import grey.crud.model.Course;
import grey.crud.services.CompanyService;
import grey.crud.services.CourseService;
import grey.crud.util.CourseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/*
grey.crud.controllers
Tarih: 06.06.2022, Saat: 3:49, Author: Grey 
*/
@Controller
@RequestMapping("/api")
public class CourseController {

    private final CourseValidator courseValidator;
    private final CourseService courseService;
    private final CompanyService companyService;

    @Autowired
    public CourseController(CourseValidator courseValidator, CourseService courseService, CompanyService companyService) {
        this.courseValidator = courseValidator;
        this.courseService = courseService;
        this.companyService = companyService;
    }

    @GetMapping("/course")
    public String showAllCourse(Model model) {
        model.addAttribute("showallcourse", courseService.showAll());
        return "courses/allcourse";

    }

    @GetMapping("/company/{companyid}/course")
    public String showCourse(@PathVariable int companyid, Model model) {
        model.addAttribute("showcoursebycompany", courseService.findAllByCompanyId(companyid));
        return "courses/bycompanyid";

    }

    @GetMapping("/course/{id}")
    public String showById(@PathVariable("id") int id, Model model) {
        model.addAttribute("showbyidnew", courseService.getById(id));
        return "courses/byid";
    }

    //create
    @GetMapping("/course/new")
    public String createCourse(Model model) {
        model.addAttribute("createcourse", new Course());
        List<Company> companyList = companyService.showAll();
        model.addAttribute("companyList", companyList);
        return "courses/save";
    }
    @PostMapping("/course")
    public String saveCourse(@ModelAttribute("createcourse") @Valid Course course, BindingResult bindingResult) {
        courseValidator.validate(course, bindingResult);
        if(bindingResult.hasErrors()){
            return "courses/save";
        }
        courseService.saveCourse(course);
        return "redirect:/api/course";

    }
    //edit update
    @GetMapping("/course/{id}/edit")
    public String editCourse(Model model, @PathVariable("id") int id) {
        model.addAttribute("updatecourse", courseService.getById(id));
        List<Company> companyList = companyService.showAll();
        model.addAttribute("companylist", companyList);
        return "/courses/edit";
    }

    @PostMapping("/course/{id}")
    public String updateCourse(@ModelAttribute("updatecourse") Course course,
                                @PathVariable("id") int id) {

//        courseService.updateCourse(course);
        courseService.updateCourse(id, course);
        return "redirect:/api/course";
    }

    @DeleteMapping("/course/{id}")
    public String deletCourse(@PathVariable("id") int id) {
        courseService.deleteCourse(id);
        return "redirect:/api/course";
    }


}
