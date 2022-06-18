package grey.crud.repositories;

import grey.crud.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
grey.crud.repositories
Tarih: 04.06.2022, Saat: 23:56, Author: Grey 
*/

@Repository
public interface CompanyRepo extends JpaRepository<Company, Integer> {

}
