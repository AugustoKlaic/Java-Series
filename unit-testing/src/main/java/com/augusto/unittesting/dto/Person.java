package com.augusto.unittesting.dto;

public class Person {

    private Long id;
    private String name;
    private Integer age;

    public Person() {
    }

    public Person(Long id, String name, Integer age) {
        this();
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
