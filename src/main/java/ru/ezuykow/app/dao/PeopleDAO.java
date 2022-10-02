package ru.ezuykow.app.dao;

import org.springframework.stereotype.Component;
import ru.ezuykow.app.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PeopleDAO {
    private static int PEOPLE_COUNT;
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/first_db";
    private static final String DB_USERNAME = "ezuykow";
    private static final String DB_PASSWORD = "579940974";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Person> index() {
        List<Person> people = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            final String SQL = "SELECT * FROM person";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                var p = new Person();

                p.setId(resultSet.getInt("id"));
                p.setName(resultSet.getString("name"));
                p.setEmail(resultSet.getString("email"));
                p.setAge(resultSet.getInt("age"));

                people.add(p);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return people;
    }

    public Person show(int id) {
        return null;
    }

    public void add(Person p) {
        try {
            Statement statement = connection.createStatement();
            final String SQL = "INSERT INTO person VALUES (" +
                    p.getId() +
                    ", '" + p.getName() +
                    "' , '" + p.getEmail() +
                    "' , " + p.getAge() +
                    ");";
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(int id, Person updatedPerson) {
        Person oldPerson = show(id);
        oldPerson.setName(updatedPerson.getName());
        oldPerson.setAge(updatedPerson.getAge());
        oldPerson.setEmail(updatedPerson.getEmail());
    }

    public void delete(int id) {
    }
}
