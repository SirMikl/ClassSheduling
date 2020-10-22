package com.ga;

import com.ga.domain.Class;

import java.util.ArrayList;

public class Driver {
    public static final int POPULATION_SIZE = 9;
    public static final double MUTATION_RATE = 0.1;
    public static final double CROSSOVER_RATE = 0.9;
    public static final int TOURNAMENT_SELECTION_SIZE = 3;
    public static final int NUMB_OF_ELITE_SHEDULES = 1;
    private Data data;
    private int sheduleNumb = 0;
    private int classNumb = 1;
    public static void main(String[] args) {
        Driver driver = new Driver();
        driver.data = new Data();
        int generationNumb = 0;
        driver.printAvailableData();
        System.out.println("> Generation # " + generationNumb);
        System.out.print("   Schedule # |                                                         ");
        System.out.print("Classes [dept,class,room,instructor,meeting-time]                     ");
        System.out.println("                                             | Fitness | Conflicts");
        System.out.print("------------------------------------------------------------------");
        System.out.println("------------------------------------------------------------------");
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(driver.data);
        Population population = new Population(Driver.POPULATION_SIZE, driver.data).sortByFitness();
        population.getShedules().forEach(schedule->System.out.println("           " +driver.sheduleNumb++ + "     | "+schedule+"  |  " +
                String.format("%.5f", schedule.getFitness()) + "  |  "+schedule.getNumberOfConflicts()));
        driver.printScheduleAsTable(population.getShedules().get(0), generationNumb);
        driver.classNumb = 1;
        while (population.getShedules().get(0).getFitness() !=1.0){
            System.out.println("> Generation # " + ++generationNumb);
            System.out.print("  Schedule # |                                                             ");
            System.out.print("Classes [dept,class,room,instructor,meeting-time]                        ");
            System.out.println("                                   |  Fitness | Conflicts");
            System.out.print("-------------------------------------------------------------------------");
            System.out.println("------------------------------------------------------------------------");
            population = geneticAlgorithm.evolve(population).sortByFitness();
            driver.sheduleNumb = 0;
            population.getShedules().forEach(shedule -> System.out.println("       "+driver.sheduleNumb++ +
                                                                 "     |  " + shedule + "  |   " +
                                                                  String.format("%.5f", shedule.getFitness()) +
                                                                   "  |  "+shedule.getNumberOfConflicts()));
            driver.printScheduleAsTable(population.getShedules().get(0), generationNumb);
            driver.classNumb = 1;
        }
    }
    private void printScheduleAsTable(Shedule shedule, int generation){
        ArrayList<Class> classes = shedule.getClasses();
        System.out.print("\n                         ");
        System.out.println("Class # | Dept | Course (number, max # of students) | Room (Capacity) |  Instructor (Id)  |  Meeting Time (Id) ");
        System.out.print("                           ");
        System.out.print("-------------------------------------------------------------------------");
        System.out.println("------------------------------------------------------------------------");
        classes.forEach(x->{
            int majorIndex = data.getDepts().indexOf(x.getDept());
            int coursesIndex = data.getCourses().indexOf(x.getCourse());
            int roomIndex = data.getRooms().indexOf(x.getRoom());
            int instructorIndex = data.getInstructors().indexOf(x.getInstructor());
            int meetingTimeIndex = data.getMeetingTimes().indexOf(x.getMeetingTime());
            System.out.print("                         ");
            System.out.print(String.format("  %1$02d  ", classNumb)+"  |  ");
            System.out.print(String.format("%1$4s", data.getDepts().get(majorIndex).getName())+"  |  ");
            System.out.print(String.format("%1$21s", data.getCourses().get(coursesIndex).getName() +
                    " (" + data.getCourses().get(coursesIndex).getNumber()+ ", "+
                    x.getCourse().getMaxNumberofStudents()) + ")      | ");
            System.out.print(String.format("%1$10s", data.getRooms().get(roomIndex).getNumber() +
                    " (" + x.getRoom().getSeatingCapacity()) + ")     | ");
            System.out.print(String.format("%1$15s", data.getInstructors().get(instructorIndex).getName()+
                    " ("+data.getInstructors().get(instructorIndex).getId()+")")+"  |  ");
            System.out.println(data.getMeetingTimes().get(meetingTimeIndex).getTime()+
                    " ("+data.getMeetingTimes().get(meetingTimeIndex).getId()+")");
            classNumb++;
        });
        if (shedule.getFitness() ==1) System.out.println("> Solution found in " + (generation+1)+ " generations");
        System.out.print("----------------------------------------------------------------------------------");
        System.out.println("------------------------------------------------------------------------------------");
    }
    private void printAvailableData(){
        System.out.println("Available Departments ==>");
        data.getDepts().forEach(x->System.out.println("name: "+x.getName()+", courses: "+x.getCourses()));
        System.out.println("\nAvailable Courses ==>");
        data.getCourses().forEach(x->System.out.println("course #: "+x.getNumber()+", name: "+x.getName()+"max of students: "
                + x.getMaxNumberofStudents()+", instructors: "+x.getInstructors()));
        System.out.println("\nAvailable rooms ==>");
        data.getRooms().forEach(x->System.out.println("room #: "+x.getNumber()+", max seating capacity: "+x.getSeatingCapacity()));
        System.out.println("\nAvailable instructors ==>");
        data.getInstructors().forEach(x->System.out.println("id: "+x.getId()+", name: "+x.getName()));
        System.out.println("\nAvailable Meeting Times ==>");
        data.getMeetingTimes().forEach(x->System.out.println("id: "+x.getId()+", Meeting Time: "+x.getTime()));
        System.out.print("----------------------------------------------------------------------------------");
        System.out.println("------------------------------------------------------------------------------------");
        //Description
    }
}
