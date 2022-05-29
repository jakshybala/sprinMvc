package grey.crud.dao;

import grey.crud.model.Person;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
grey.crud.dao
Tarih: 28.05.2022, Saat: 4:03, Author: Grey 
*/
@Component
public class PersonDao {
    private static int AutoIncrement;


//    private List<Person> personList;
//    {
//        personList = new ArrayList<>();
//        personList.add(new Person(++AutoIncrement, "Asan", 21, "asa@mail.ru"));
//        personList.add(new Person(++AutoIncrement, "Samat",23, "sam@mail.ru"));
//        personList.add(new Person(++AutoIncrement, "Barsbek", 51, "bars@mail.ru"));
//    }
    private static final String URL="jdbc:postgresql://localhost:5432/grey_db";
    private static final String USER="postgres";
    private static final String PASS="PostgresJava";

    private static Connection connection;
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL,USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Person> indexOfAllPeople() {
        List<Person> personList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT * FROM Person";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()){
                Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));

                personList.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personList;

    }
    public Person showById(int id) throws Exception {
//        return personList.stream().filter(personList -> personList.getId() == id).findAny().orElseThrow(() ->
//                new Exception("No person by that id: " + id));
        Person person = null;
        try {
        PreparedStatement preparedStatement = connection.prepareStatement(
                                                "SELECT * FROM Person WHERE id = ?");
        preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            person = new Person();
            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setAge(resultSet.getInt("age"));
            person.setEmail(resultSet.getString("email"));


        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return person;
    }

    public void save(Person newPerson) {
//        newPerson.setId(++AutoIncrement);
//        personList.add(newPerson);

        try {
        PreparedStatement preparedStatement = connection.prepareStatement
                ("INSERT INTO Person VALUES (1, ?, ?, ?)");
        preparedStatement.setString(1, newPerson.getName());
        preparedStatement.setInt(2, newPerson.getAge());
        preparedStatement.setString(3, newPerson.getEmail());
        preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(int id, Person updatePerson) throws Exception {
//        Person personUpdateBe = showById(id);
//        personUpdateBe.setName(updatePerson.getName());
//        personUpdateBe.setAge(updatePerson.getAge());
//        personUpdateBe.setEmail(updatePerson.getEmail());

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE Person SET name=?, age=?, email=? WHERE id=?");

            preparedStatement.setString(1,updatePerson.getName());
            preparedStatement.setInt(2, updatePerson.getAge());
            preparedStatement.setString(3, updatePerson.getEmail());
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



    }

    public void delete(int id) {
//        personList.removeIf(person -> person.getId() == id);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("DELETE FROM Person WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
