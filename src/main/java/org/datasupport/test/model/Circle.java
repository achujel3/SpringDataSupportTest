package org.datasupport.test.model;

import org.springframework.util.StringUtils;

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
        return "Circle (" + this.getId() + ", " + StringUtils.trimAllWhitespace(this.getName()) + ").";
    }
}
