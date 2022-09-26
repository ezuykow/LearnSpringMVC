package ru.ezuykow.app.models;


import javax.validation.constraints.*;

public class Person {
    private int id;
    @NotEmpty(message = "Field should not be empty!")
    @Size(min = 2, max = 30, message = "Name is not valid!")
    private String name;

    @NotEmpty(message = "Email is not valid!")
    @Email(message = "Email is not valid!")
    private String email;

    @Min(value = 0, message = "Name is not valid!")
    @Max(value = 100, message = "Name is not valid!")
    private int age;

    public Person() {}
    public Person(int id, String name, String email, int age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
