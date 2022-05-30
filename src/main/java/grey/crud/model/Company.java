package grey.crud.model;

/*
grey.crud.model
Tarih: 30.05.2022, Saat: 18:01, Author: Grey 
*/


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String country;
}
