package com.ga.domain;

import java.util.ArrayList;

public class Course {
    private String number = null;
    private String name = null;
    private int maxNumberofStudents;
    private ArrayList<Instructor> instructors;

    public Course(String number, String name, ArrayList<Instructor> instructors, int maxNumberofStudents) {
        this.number = number;
        this.name = name;
        this.instructors = instructors;
        this.maxNumberofStudents = maxNumberofStudents;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxNumberofStudents() {
        return maxNumberofStudents;
    }

    public void setMaxNumberofStudents(int maxNumberofStudents) {
        this.maxNumberofStudents = maxNumberofStudents;
    }

    public ArrayList<Instructor> getInstructors() {
        return instructors;
    }

    public void setInstructors(ArrayList<Instructor> instructors) {
        this.instructors = instructors;
    }

    @Override
    public String toString() {
        return name;
    }
}
