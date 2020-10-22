package com.ga;

import com.ga.domain.Class;
import com.ga.domain.Department;

import java.util.ArrayList;

public class Shedule {
    private ArrayList<Class> classes;
    private boolean isFitnessChanged = true;
    private double fitness = -1;
    private int numberOfConflicts = 0;
    private int classNumb = 0;
    private Data data;

    public Data getData() {
        return data;
    }

    public Shedule(Data data) {
        this.data = data;
        classes = new ArrayList<Class>(data.getNumberOfClasses());
    }

    public Shedule initialize(){
        new ArrayList<Department>(data.getDepts()).forEach(dept -> {
            dept.getCourses().forEach( course -> {
                Class newClass = new Class(classNumb++, dept, course);
                newClass.setMeetingTime(data.getMeetingTimes().get((int)(data.getMeetingTimes().size()*Math.random())));
                newClass.setRoom(data.getRooms().get((int)(data.getRooms().size()*Math.random())));
                newClass.setInstructor(course.getInstructors().get((int)(course.getInstructors().size()*Math.random())));
                classes.add(newClass);
            });
        });
        return  this;
    }

    public int getNumberOfConflicts() {
        return numberOfConflicts;
    }

    public ArrayList<Class> getClasses() {
        isFitnessChanged = true;
        return classes;
    }

    public double getFitness() {
        if (isFitnessChanged == true){
            fitness = calculateFitness();
            isFitnessChanged=false;
        }
        return fitness;
    }

    private double calculateFitness(){
        numberOfConflicts = 0;
        classes.forEach(x -> {
            if (x.getRoom().getSeatingCapacity()<x.getCourse().getMaxNumberofStudents())
                numberOfConflicts++;
            classes.stream().filter(y-> classes.indexOf(y)>=classes.indexOf(x)).forEach(y->{
                if (x.getMeetingTime() == y.getMeetingTime() && x.getId() != y.getId()){
                    if (x.getRoom() == y.getRoom()) numberOfConflicts++;
                    if (x.getInstructor() == y.getInstructor()) numberOfConflicts++;
                }
            });
        });
        return 1 / (double)(numberOfConflicts+1);
    }

    @Override
    public String toString() {
        String returnValue = new String();
        for (int x = 0; x < classes.size()-1; x++){
            returnValue += classes.get(x) + ", ";
        }
        returnValue += classes.get(classes.size()-1);
        return  returnValue;
    }
}
