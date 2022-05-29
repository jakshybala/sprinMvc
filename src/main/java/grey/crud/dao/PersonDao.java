package grey.crud.dao;

import grey.crud.model.Person;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

/*
grey.crud.dao
Tarih: 28.05.2022, Saat: 4:03, Author: Grey 
*/
@Component
public class PersonDao {
    private static int AutoIncrement;
    private List<Person> personList;
    {
        personList = new ArrayList<>();
        personList.add(new Person(++AutoIncrement, "Asan", 21, "asa@mail.ru"));
        personList.add(new Person(++AutoIncrement, "Samat",23, "sam@mail.ru"));
        personList.add(new Person(++AutoIncrement, "Barsbek", 51, "bars@mail.ru"));
    }
    public List<Person> indexOfAllPeople() {
        return personList;
    }
    public Person showById(int id) throws Exception {
        return personList.stream().filter(personList -> personList.getId() == id).findAny().orElseThrow(() ->
                new Exception("No person by that id: " + id));
    }

    public void save(Person newPerson) {
        newPerson.setId(++AutoIncrement);
        personList.add(newPerson);
    }

    public void update(int id, Person updatePerson) throws Exception {
        Person personUpdateBe = showById(id);
        personUpdateBe.setName(updatePerson.getName());
        personUpdateBe.setAge(updatePerson.getAge());
        personUpdateBe.setEmail(updatePerson.getEmail());

    }

    public void delete(int id) {
        personList.removeIf(person -> person.getId() == id);
    }
}
