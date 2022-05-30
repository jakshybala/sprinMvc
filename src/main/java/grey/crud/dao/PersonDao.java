package grey.crud.dao;

import grey.crud.model.Person;
import grey.crud.model.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/*
grey.crud.dao
Tarih: 28.05.2022, Saat: 4:03, Author: Grey
*/
@Component
public class PersonDao {

    public final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Person> indexOfAllPeople() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));

    }
    public Person showById(int id) {

        return jdbcTemplate.query("SELECT * FROM Person WHERE id = ?", new Object[]{id}, new PersonMapper())
                .stream().findAny().orElse(null);

    }
    public Optional<Person> showEmail(String email) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE email = ?", new Object[] {email},
            new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public void save(Person newPerson){
        jdbcTemplate.update("INSERT INTO Person(name , age, email) VALUES(?, ?, ?)",
                                        newPerson.getName(), newPerson.getAge(), newPerson.getEmail());

    }

    public void update(int id, Person updatePerson) {
        jdbcTemplate.update("UPDATE Person SET name =?, age = ?, email =? WHERE id =?",
                                    updatePerson.getName(), updatePerson.getAge(), updatePerson.getEmail(),id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id = ?", id);

    }
}
