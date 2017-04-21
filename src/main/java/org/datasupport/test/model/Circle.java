package org.datasupport.test.model;

import org.springframework.util.StringUtils;

import java.util.List;

public class Circle {

    private int id;
    private String name;

    public Circle() {

    }

    public Circle(int id, String name) {
        setId(id);
        setName(name);
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

    @Override
    public String toString() {
        return "Circle (" + this.getId() + ", " + StringUtils.trimTrailingWhitespace(this.getName()) + ").";
    }

    public static void toString(List<Circle> circles) {
        System.out.println("List of circles: ");
        for (Circle circle : circles) {
            System.out.println(circle);
        }
    }
}
