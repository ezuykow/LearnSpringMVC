package ru.ezuykow.app.dao;

import org.springframework.stereotype.Component;
import ru.ezuykow.app.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PeopleDAO {
    private static int PEOPLE_COUNT;
    private List<Person> people;

    {
        people = new ArrayList<>();

        people.add(new Person(++PEOPLE_COUNT, "Bob", "bob@gmail.com", 23));
        people.add(new Person(++PEOPLE_COUNT, "Mary", "mary@gmail.com", 32));
        people.add(new Person(++PEOPLE_COUNT, "Kate", "kate@gmail.com", 41));
        people.add(new Person(++PEOPLE_COUNT, "Ivan", "ivan@gmail.com", 16));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream()
                .filter(person -> person.getId() == id)
                .findAny()
                .orElse(null);
    }

    public void add(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(int id, Person updatedPerson) {
        Person oldPerson = show(id);
        oldPerson.setName(updatedPerson.getName());
        oldPerson.setAge(updatedPerson.getAge());
        oldPerson.setEmail(updatedPerson.getEmail());
    }

    public void delete(int id) {
        people.removeIf(p -> p.getId() == id);
    }
}
