package grey.crud.controllers;

/*
grey.crud.controllers
Tarih: 16.06.2022, Saat: 21:10, Author: Grey 
*/

import grey.crud.model.Course;
import grey.crud.model.Teacher;
import grey.crud.services.CourseService;
import grey.crud.services.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api")
public class TeacherController {
    private final TeacherService teacherService;
    private final CourseService courseService;

    public TeacherController(TeacherService teacherService, CourseService courseService) {
        this.teacherService = teacherService;
        this.courseService = courseService;
    }
    @GetMapping("/teacher")
    public String showAll(Model model){
        model.addAttribute("allteachers", teacherService.showAll());
        return "teachers/allteacher";

    }
    @GetMapping("/teacher/{id}")
    public String showById(@PathVariable("id") int id,  Model model) {
        model.addAttribute("showbyid", teacherService.getById(id));
        return "teachers/byidteacher";
    }
    //create
    @GetMapping("teacher/new")
    public String createTeacher(Model model) {
        model.addAttribute("createteacher", new Teacher());
        List<Course> courseList = courseService.showAll();
        List<Course> busyCourseList = new ArrayList<>();
        for (Course course : courseList) {
            if (course.getTeacher() == null) {
                busyCourseList.add(course);
            }
        }
        model.addAttribute("coureslist", busyCourseList);
        return "teachers/saveteacher";
    }
    @PostMapping("/teacher")
    public String saveTeacher(@ModelAttribute("createteacher") Teacher teacher) {
        teacherService.creteTeacher(teacher);
        return "redirect:/api/teacher";
    }
    @GetMapping("/teacher/{id}/edit")
    public String editTeacher(@PathVariable("id") int id, Model model) {
        model.addAttribute("teacheredit", teacherService.getById(id));
        List<Course> courseList = courseService.showAll();
        List<Course> newCourseList = new ArrayList<>();
        courseList.stream().forEach(course -> {if (course.getTeacher() == null)newCourseList.add(course);});
        model.addAttribute("coureslist", newCourseList);
        return "/teachers/editteacher";

    }
    @PostMapping("/teacher/{id}")
    public String updateTeacher(@ModelAttribute("teacheredit") Teacher teacher,
                                @PathVariable("id") int id) {
        teacherService.updateTeacher(id, teacher);
        return "redirect:/api/teacher";

    }
    @DeleteMapping("/teacher/{id}")
    public String deletTecher(@PathVariable("id") int id) {
        teacherService.deletById(id);
        return "redirect:/api/teacher";
    }

}
