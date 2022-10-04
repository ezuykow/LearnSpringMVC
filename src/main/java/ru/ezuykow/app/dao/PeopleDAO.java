package ru.ezuykow.app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.ezuykow.app.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PeopleDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PeopleDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM person WHERE id=?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class))
                .stream()
                .findAny()
                .orElse(null);
    }

    public void add(Person p) {
        jdbcTemplate.update("INSERT INTO person VALUES (?, ?, ?, ?)",
                p.getId(),
                p.getName(),
                p.getEmail(),
                p.getAge());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE person SET name=?, email=?, age=? WHERE id=?",
                updatedPerson.getName(),
                updatedPerson.getEmail(),
                updatedPerson.getAge(),
                id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE id=?", id);
    }

    public void add10Person() {
        List<Person> newPersons = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            newPersons.add(new Person(i, "Name" + i, "name" + i + "@mail.ru", 30));
        }

        jdbcTemplate.batchUpdate("INSERT INTO person VALUES (?, ?, ?, ?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setInt(1, newPersons.get(i).getId());
                        ps.setString(2, newPersons.get(i).getName());
                        ps.setString(3, newPersons.get(i).getEmail());
                        ps.setInt(4, newPersons.get(i).getAge());
                    }

                    @Override
                    public int getBatchSize() {
                        return newPersons.size();
                    }
                });
    }
}