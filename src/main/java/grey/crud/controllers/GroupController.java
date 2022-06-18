package grey.crud.controllers;

/*
grey.crud.controllers
Tarih: 16.06.2022, Saat: 21:10, Author: Grey 
*/

import grey.crud.model.Course;
import grey.crud.model.Group;
import grey.crud.model.Teacher;
import grey.crud.services.CourseService;
import grey.crud.services.GroupService;
import grey.crud.services.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
public class GroupController {
    private final GroupService groupService;
    private final CourseService courseService;
    private final TeacherService teacherService;


    public GroupController(GroupService groupService, CourseService courseService, TeacherService teacherService) {
        this.groupService = groupService;
        this.courseService = courseService;
        this.teacherService = teacherService;
    }



    @GetMapping("/group")
    public String showAll(Model model){
        model.addAttribute("allgroup", groupService.showAll());
        model.addAttribute("courselist", courseService.showAll());
        return "group/allgroup";

    }

    @GetMapping("/group/{id}")
    public String showById(@PathVariable("id") int id,  Model model) {
        model.addAttribute("showbyid", groupService.getById(id));
        return "group/byidgroup";
    }
    //create
    @GetMapping("group/new")
    public String createGroup(Model model) {
        model.addAttribute("creategroup", new Group());
        List<Course> courseList = courseService.showAll();
        model.addAttribute("courseListpar", courseList);
        return "group/savegroup";
    }
    @PostMapping("/group")
    public String saveGroup(@ModelAttribute("creategroup") Group group) {
       // group.setCourse1(courseService.getById(group.getCourseId()));
        groupService.createGroup(group);
        return "redirect:/api/group";
    }
    @GetMapping("/group/{id}/edit")
    public String editGroup(@PathVariable("id") int id, Model model) {
        model.addAttribute("groupedit", groupService.getById(id));
        List<Course> courseList = courseService.showAll();
        model.addAttribute("coureslistpar", courseList);
//        model.addAttribute("selectedCourseid", courseService.selectedCourse(courseList));

        return "/group/editgroup";

    }
    @PostMapping("/group/{id}")
    public String updateGroup(@ModelAttribute("groupupdate") Group group,
                                @PathVariable("id") int id) {
        groupService.updateGroup(id, group);
        return "redirect:/api/group";

    }
    @DeleteMapping("/group/{id}")
    public String deletGroup(@PathVariable("id") int id) {
        groupService.deletById(id);
        return "redirect:/api/group";
    }

}
