package grey.crud.model;


import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/*
grey.crud.model
Tarih: 28.05.2022, Saat: 4:01, Author: Grey 
*/
public class Person {
    private int id;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 3, max = 34, message = "Name contain 3 or more letter!")
    private String name;
    @Min(value = 18, message = "Age should be greater than 18+")
    private int age;
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Write email address like exaple@mail.ru")
    private String email;

    public Person() {
    }

    public Person(int id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
