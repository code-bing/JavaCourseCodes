package org.github.yibing.spring.ioc.autowire;

import org.springframework.stereotype.Component;

@Component("student")

public class Student {
    private String id;
    private String name;

    public void init() {
        System.out.println("hello..........."+this.name);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

