package uk.ac.cam.sp794.oopjava.tick4star;

import uk.ac.cam.acr31.life.World;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
public class Statistics{
        private double maximumGrowthRate = 0;
        private double maximumDeathRate = 0;
        private int loopStart;
        private int loopEnd;
        private int maximumPopulation = 0;
        private int minimumPopulation = 100000000;
        private ArrayList<Double> population, changes, rates;
        public List<Double> getPopulations(){
                return population;
        }
        public List<Double> getChanges(){
                return changes;
        }
        public List<Double> getRates(){
                return rates;
        }
        public Statistics(World w){
                population = new ArrayList<Double>();
                changes = new ArrayList<Double>();
                rates = new ArrayList<Double>();

                final int generationStart = 0;
                HashMap<String,Integer> pastWorlds = new HashMap<String,Integer>();
                pastWorlds.put(w.toString(), generationStart);
                int currentGeneration = generationStart;
                int previousPopulation = w.getPopulation();
                population.add((double)previousPopulation);
                while(true){
                        w = w.nextGeneration(0);
                        currentGeneration ++;
                        int currentPopulation = w.getPopulation();
                        maximumPopulation = Math.max(maximumPopulation, currentPopulation);
                        minimumPopulation = Math.min(minimumPopulation, currentPopulation);
                        double change = ((double)currentPopulation - previousPopulation)/previousPopulation;
                        if(population.size()<100)
                                population.add((double)currentPopulation);
                        if(changes.size()<100)
                                changes.add((double)currentPopulation - previousPopulation);
                        if(rates.size()<100)
                                rates.add(change);
                
                        if(change > 0)
                                maximumGrowthRate = Math.max(maximumGrowthRate, change);
                        else
                                maximumDeathRate = Math.max(maximumDeathRate, -change);

                        if(pastWorlds.containsKey(w.toString())){
                                loopStart = pastWorlds.get(w.toString());
                                loopEnd = currentGeneration - 1;
                                if(rates.size()>=100)
                                        break;
                        }
                        else{
                                pastWorlds.put(w.toString(), currentGeneration);
                        }
                        previousPopulation = currentPopulation;
                }
                
        }
        public double getMaximumGrowthRate(){
                return maximumGrowthRate;

        }
        public double getMaximumDeathRate(){
                return maximumDeathRate;

        }
        public int getLoopStart(){
                return loopStart;

        }
        public int getLoopEnd(){
                return loopEnd;

        }
        public int getMaximumPopulation(){
                return maximumPopulation;

        }
        public int getMinimumPopulation(){
                return minimumPopulation;
        }
}
