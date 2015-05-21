package com.spencer.project1;

import com.google.gson.annotations.SerializedName;

/**
 * Created on 5/20/15.
 *
 * @author Spencer Amann
 */
public class Person {
    private String name;

    @SerializedName("last_name")
    private String lastName;

    public Person() {
        super();
    }

    public Person(String name, String lastName) {
        super();
        this.name = name;
        this.lastName = lastName;
    }
    @Override
    public String toString() {
        return "Name: " + name + ", Last Name: " + lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
