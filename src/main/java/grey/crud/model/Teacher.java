package grey.crud.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/*
grey.crud.model
Tarih: 16.06.2022, Saat: 20:06, Author: Grey 
*/
@Entity
@Table(name = "Teacher")
@Getter
@Setter
@ToString
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Name should not be empty")
    private String name;

    @Column(name = "last_Name")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 3, max = 34, message = "Name contain 3 or more letter!")
    private String lastName;

    @Column(name = "email")
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Write email address like exaple@mail.ru")
    private String email;
    @OneToOne()
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;


}
