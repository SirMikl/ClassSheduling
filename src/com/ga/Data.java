package com.ga;

import com.ga.domain.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Data {
    private ArrayList<Room> rooms;
    private ArrayList<Instructor> instructors;
    private ArrayList<Course> courses;
    private ArrayList<Department> depts;
    private ArrayList<MeetingTime> meetingTimes;
    private int numberOfClasses = 0;

    public Data() {
        initialize();
    }

    private Data initialize(){
        Room room1 = new Room("R1", 25);
        Room room2 = new Room("R2", 45);
        Room room3 = new Room("R3", 35);
        rooms = new ArrayList<Room>(Arrays.asList(room1, room2, room3));

        MeetingTime meetingTime1 = new MeetingTime("MT1", "MWF 09:00 - 10:00");
        MeetingTime meetingTime2 = new MeetingTime("MT2", "MWF 10:00 - 11:00");
        MeetingTime meetingTime3 = new MeetingTime("MT3", "TTH 09:00 - 10:30");
        MeetingTime meetingTime4 = new MeetingTime("MT4", "TTH 10:30 - 12:00");
        meetingTimes = new ArrayList<MeetingTime>(Arrays.asList(meetingTime1, meetingTime2, meetingTime3, meetingTime4));

        Instructor instructor1 = new Instructor("I1", "Dr. James Web");
        Instructor instructor2 = new Instructor("I2", "Mr. Mike Brown");
        Instructor instructor3 = new Instructor("I3", "Dr. Steve Day");
        Instructor instructor4 = new Instructor("I4", "Mrs. Jane Doe");
        instructors = new ArrayList<Instructor>(Arrays.asList(instructor1, instructor2, instructor3, instructor4));

        Course course1 = new Course("C1", "325K", new ArrayList<Instructor>(Arrays.asList(instructor1, instructor2)), 25);
        Course course2 = new Course("C2", "319K", new ArrayList<Instructor>(Arrays.asList(instructor1, instructor2, instructor3)), 35);
        Course course3 = new Course("C3", "462k", new ArrayList<Instructor>(Arrays.asList(instructor1, instructor2)), 25);
        Course course4 = new Course("C4", "464K", new ArrayList<Instructor>(Arrays.asList(instructor3, instructor4)), 30);
        Course course5 = new Course("C5", "360C", new ArrayList<Instructor>(Arrays.asList(instructor4)), 35);
        Course course6 = new Course("C6", "303K", new ArrayList<Instructor>(Arrays.asList(instructor1, instructor3)), 45);
        Course course7 = new Course("C7", "303L", new ArrayList<Instructor>(Arrays.asList(instructor2, instructor4)), 45);
        courses = new ArrayList<Course>(Arrays.asList(course1, course2, course3, course4, course5, course6, course7));

        Department dept1 = new Department("MATH", new ArrayList<Course>(Arrays.asList(course1, course3)));
        Department dept2 = new Department("EE", new ArrayList<Course>(Arrays.asList(course2, course4, course5)));
        Department dept3 = new Department("PHY", new ArrayList<Course>(Arrays.asList(course6, course7)));
        depts = new ArrayList<Department>(Arrays.asList(dept1, dept2, dept3));

        depts.forEach(x -> numberOfClasses+=x.getCourses().size());
        return this;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public ArrayList<Instructor> getInstructors() {
        return instructors;
    }

    public void setInstructors(ArrayList<Instructor> instructors) {
        this.instructors = instructors;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public ArrayList<Department> getDepts() {
        return depts;
    }

    public void setDepts(ArrayList<Department> depts) {
        this.depts = depts;
    }

    public ArrayList<MeetingTime> getMeetingTimes() {
        return meetingTimes;
    }

    public void setMeetingTimes(ArrayList<MeetingTime> meetingTimes) {
        this.meetingTimes = meetingTimes;
    }

    public int getNumberOfClasses() {
        return numberOfClasses;
    }

    public void setNumberOfClasses(int numberOfClasses) {
        this.numberOfClasses = numberOfClasses;
    }

}
