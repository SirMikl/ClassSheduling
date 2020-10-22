package com.ga;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class GeneticAlgorithm {
    private Data data;

    public GeneticAlgorithm(Data data) {
        this.data = data;
    }

    public Population evolve(Population population){
        return mutatePopulation(crossoverPopulation(population));
    }

    Population crossoverPopulation (Population population){
        Population crossoverPopulation = new Population(population.getShedules().size(), data);
        IntStream.range(0, Driver.NUMB_OF_ELITE_SHEDULES).forEach(x->crossoverPopulation.getShedules().set(x, population.getShedules().get(x)));
        IntStream.range(Driver.NUMB_OF_ELITE_SHEDULES, population.getShedules().size()).forEach(x->{
            if (Driver.CROSSOVER_RATE>Math.random()){
                Shedule shedule1 = selectTournamentPopulation(population).sortByFitness().getShedules().get(0);
                Shedule shedule2 = selectTournamentPopulation(population).sortByFitness().getShedules().get(0);
                crossoverPopulation.getShedules().set(x, crossoverShedule(shedule1, shedule2));
            } else {
                crossoverPopulation.getShedules().set(x, population.getShedules().get(x));
            }
        });
        return crossoverPopulation;
    }

    Shedule crossoverShedule(Shedule shedule1, Shedule shedule2){
        Shedule crossoverSchedule = new Shedule(data).initialize();
        IntStream.range(0, crossoverSchedule.getClasses().size()).forEach(x->{
            if (Math.random() > 0.5) crossoverSchedule.getClasses().set(x, shedule1.getClasses().get(x));
            else crossoverSchedule.getClasses().set(x, shedule2.getClasses().get(x));
        });
        return crossoverSchedule;
    }

    Population mutatePopulation (Population population){
        Population mutatePopulation = new Population(population.getShedules().size(), data);
        ArrayList<Shedule> shedules = mutatePopulation.getShedules();
        IntStream.range(0, Driver.NUMB_OF_ELITE_SHEDULES).forEach(x->shedules.set(x, population.getShedules().get(x)));
        IntStream.range(Driver.NUMB_OF_ELITE_SHEDULES, population.getShedules().size()).forEach(x->{
            shedules.set(x, mutateShelule(population.getShedules().get(x)));
        });
        return mutatePopulation;
    }

    Shedule mutateShelule(Shedule mutateShedule){
        Shedule shedule = new Shedule(data).initialize();
        IntStream.range(0, mutateShedule.getClasses().size()).forEach(x->{
            if (Driver.MUTATION_RATE> Math.random()) mutateShedule.getClasses().set(x, shedule.getClasses().get(x));
        });
        return mutateShedule;
    }

    Population selectTournamentPopulation(Population population){
        Population tournamentPopulation = new Population (Driver.TOURNAMENT_SELECTION_SIZE, data);
        IntStream.range(0, Driver.TOURNAMENT_SELECTION_SIZE).forEach(x->{
            tournamentPopulation.getShedules().set(x, population.getShedules().get((int)(Math.random()*population.getShedules().size())));
        });
        return tournamentPopulation;
    }
}
