package grey.crud.repositories;

import grey.crud.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/*
grey.crud.repositories
Tarih: 04.06.2022, Saat: 23:56, Author: Grey 
*/

public interface CourseRepo extends JpaRepository<Course, Integer> {

    @Query("select c from Course c where c.company.id = ?1")
    List<Course> findAllByCompanyId(int companyId);
}
