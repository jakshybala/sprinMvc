package grey.crud.model;

/*
grey.crud.model
Tarih: 30.05.2022, Saat: 18:01, Author: Grey 
*/

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "Company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 3, max = 34, message = "Name must contain 3 or more letter!")
    private String name;

    @Column(name = "country")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 3, max = 34, message = "Name must contain 3 or more letter!")
    private String country;

    public Company() {

    }
    //course
    @OneToMany(mappedBy = "company")
    private List<Course> courseList;

    public Company(String name, String country) {
        this.name = name;
        this.country = country;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    public void setCourse(Course course) {
        this.courseList.add(course);
    }
}
