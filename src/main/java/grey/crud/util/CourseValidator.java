package grey.crud.util;

import grey.crud.model.Company;
import grey.crud.model.Course;
import grey.crud.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/*
grey.crud.util
Tarih: 31.05.2022, Saat: 1:27, Author: Grey 
*/@Component
public class CourseValidator implements Validator {
    private final CourseService courseService;
    @Autowired
    public CourseValidator(CourseService courseService) {
        this.courseService = courseService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return Course.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }


//    @Override
//    public void validate(Object target, Errors errors) {
//        Person person = (Person) target;
//
//        if(personDao.showEmail(person.getEmail()).isPresent()) {
//            errors.rejectValue("email", "", "This email already token");
//
//        }


    }
