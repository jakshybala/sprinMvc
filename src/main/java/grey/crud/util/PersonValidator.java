package grey.crud.util;

import grey.crud.dao.PersonDao;
import grey.crud.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/*
grey.crud.util
Tarih: 31.05.2022, Saat: 1:27, Author: Grey 
*/@Component
public class PersonValidator implements Validator {
    private final PersonDao personDao;

    @Autowired
    public PersonValidator(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if(personDao.showEmail(person.getEmail()).isPresent()) {
            errors.rejectValue("email", "", "This email already token");

        }


    }
}
