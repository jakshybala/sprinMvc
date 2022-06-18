package grey.crud.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
grey.crud.model
Tarih: 17.06.2022, Saat: 1:05, Author: Grey 
*/
@Entity
@Table(name = "Groups")
@Setter
@Getter
@ToString
public class Group  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Course should not be empty")
    @Size(min = 3, max = 34, message = "Course name must contain 3 or more letter!")
    private String name;

    @Column(name = "date_start")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateStart;

    @Column(name = "date_end")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateEnd;

    @ManyToMany
    @JoinTable(name = "Group_Course",
                joinColumns = @JoinColumn(name = "group_id"),
                inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> courseList;

    @Transient
    private int courseId;

}
