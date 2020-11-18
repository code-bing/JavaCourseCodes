package com.github.yibing.jdbc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student {
    private int id;
    private String name;

    public Student(String name) {
        this.name = name;
    }
}
