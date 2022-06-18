package grey.crud.repositories;

import grey.crud.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/*
grey.crud.repositories
Tarih: 04.06.2022, Saat: 23:56, Author: Grey 
*/

public interface PersonRepo extends JpaRepository<Person, Integer> {


}
