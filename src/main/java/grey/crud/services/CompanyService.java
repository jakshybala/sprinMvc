package grey.crud.services;

import grey.crud.model.Company;
import grey.crud.repositories.CompanyRepo;
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
public class CompanyService {

    private final CompanyRepo companyRepo;
    @Autowired
    public CompanyService(CompanyRepo companyRepo) {
        this.companyRepo = companyRepo;
    }

    public List<Company> showAll() {
        return companyRepo.findAll();

    }
    public Company getById(int id) {
        Optional<Company> findById = companyRepo.findById(id);
        return findById.orElse(null);

    }
    @Transactional
    public void saveCompany(Company newCompany) {
        companyRepo.save(newCompany);
    }
    @Transactional
    public void updateCompany(int id, Company updateCompany) {
        updateCompany.setId(id);
        companyRepo.save(updateCompany);

    }
    @Transactional
    public void deletCompany(int id) {
        companyRepo.deleteById(id);
    }

}
