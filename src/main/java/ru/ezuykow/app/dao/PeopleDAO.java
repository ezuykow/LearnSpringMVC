package ru.ezuykow.app.dao;

import org.springframework.stereotype.Component;
import ru.ezuykow.app.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PeopleDAO {
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
        Person person = null;

        try {
            PreparedStatement ps =
                    connection.prepareStatement("SELECT * FROM person WHERE id=?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            rs.next();

            person = new Person();
            person.setId(rs.getInt("id"));
            person.setName(rs.getString("name"));
            person.setEmail(rs.getString("email"));
            person.setAge(rs.getInt("age"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return person;
    }

    public void add(Person p) {
        try {
            PreparedStatement ps =
                    connection.prepareStatement("INSERT INTO person VALUES (?, ?, ?, ?)");
            ps.setInt(1, p.getId());
            ps.setString(2, p.getName());
            ps.setString(3, p.getEmail());
            ps.setInt(4, p.getAge());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(int id, Person updatedPerson) {
        try {
            PreparedStatement ps =
                    connection.prepareStatement("UPDATE person SET name=?, email=?, age=? WHERE id=?");
            ps.setString(1, updatedPerson.getName());
            ps.setString(2, updatedPerson.getEmail());
            ps.setInt(3, updatedPerson.getAge());
            ps.setInt(4, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        try {
            PreparedStatement ps =
                    connection.prepareStatement("DELETE FROM person WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
