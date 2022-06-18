package grey.crud.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/*
grey.crud.model
Tarih: 04.06.2022, Saat: 21:01, Author: Grey 
*/

@Entity
@Table(name = "Course")
@Getter
@Setter
@ToString
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "name")
    @NotEmpty(message = "Course should not be empty")
    @Size(min = 3, max = 34, message = "Course name must contain 3 or more letter!")
    private String courseName;

    @Column(name = "duration")
    @Digits(fraction = 0, integer = 10, message ="Only digits!")
    private int duration;
    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;

//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OneToOne(mappedBy = "course")
    private Teacher teacher;

    @ManyToMany(mappedBy = "courseList")
    private List<Group> groupList;


}
