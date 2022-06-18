//package grey.crud.dao;
//
//import grey.crud.model.Person;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Optional;
//
///*
//grey.crud.dao
//Tarih: 28.05.2022, Saat: 4:03, Author: Grey
//*/
//@Component
//public class PersonDao {
////    private final SessionFactory sessionFactory;
////    @Autowired
////    public PersonDao(SessionFactory sessionFactory) {
////        this.sessionFactory = sessionFactory;
////    }
//
//    @Transactional(readOnly = true)
//    public List<Person> indexOfAllPeople() {
////        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
////        EntityManager entityManager = entityManagerFactory.createEntityManager();
////        Session session = sessionFactory.getCurrentSession();
////        List<Person> peopleList = session.createQuery("SELECT p from Person p ", Person.class).getResultList();
////        return peopleList;
//    }
//    public Person showById(int id) {
//
////        return sesionFactory.query("SELECT * FROM Person WHERE id = ?", new Object[]{id}, new PersonMapper())
////                .stream().findAny().orElse(null);
//        return null;
//
//
//    }
//    public Optional<Person> showEmail(String email) {
////        return jdbcTemplate.query("SELECT * FROM Person WHERE email = ?", new Object[] {email},
////            new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
//        return null;
//    }
//
//    public void save(Person newPerson){
////        jdbcTemplate.update("INSERT INTO Person(name , age, email) VALUES(?, ?, ?)",
////                                        newPerson.getName(), newPerson.getAge(), newPerson.getEmail());
//
//    }
//
//    public void update(int id, Person updatePerson) {
////        jdbcTemplate.update("UPDATE Person SET name =?, age = ?, email =? WHERE id =?",
////                                    updatePerson.getName(), updatePerson.getAge(), updatePerson.getEmail(),id);
//    }
//
//    public void delete(int id) {
////        jdbcTemplate.update("DELETE FROM Person WHERE id = ?", id);
//
//    }
//}
