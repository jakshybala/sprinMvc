package grey.crud.services;
import grey.crud.model.Person;
import grey.crud.repositories.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

/*
grey.crud.services
Tarih: 05.06.2022, Saat: 0:00, Author: Grey 
*/
@Service
@Transactional(readOnly = true)
public class PersonService {

    private final PersonRepo personRepo;
    @Autowired
    public PersonService(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    public List<Person> showAll() {
        return personRepo.findAll();

    }
    public Person getById(int id) {
        Optional<Person> findById = personRepo.findById(id);
        return findById.orElse(null);

    }
    @Transactional
    public void savePerson(Person newCompany) {
        personRepo.save(newCompany);
    }
    @Transactional
    public void updatePerson(int id, Person updatePerson) {
        updatePerson.setId(id);
        personRepo.save(updatePerson);

    }
    @Transactional
    public void deletPerson(int id) {
        personRepo.deleteById(id);
    }

}
