package com.ga;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class Population {
    private ArrayList<Shedule> shedules;

    public Population(int size, Data data) {
        shedules = new ArrayList<Shedule>(size);
        IntStream.range(0, size).forEach(x->shedules.add(new Shedule(data).initialize()));
    }

    public ArrayList<Shedule> getShedules() {
        return shedules;
    }

    public Population sortByFitness(){
        shedules.sort((shedule1, shedule2)->{
            int returnValue = 0;
            if (shedule1.getFitness()>shedule2.getFitness()) returnValue = -1;
            else if (shedule1.getFitness()<shedule2.getFitness()) returnValue = 1;
            return returnValue;
        });
        return this;
    }
}
