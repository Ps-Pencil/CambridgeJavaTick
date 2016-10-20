package uk.ac.cam.sp794.oopjava.tick3star;

import uk.ac.cam.acr31.life.World;

import java.util.HashMap;

public class Statistics{
        private double maximumGrowthRate = 0;
        private double maximumDeathRate = 0;
        private int loopStart;
        private int loopEnd;
        private int maximumPopulation = 0;
        public Statistics(World w){
                final int generationStart = 0;
                HashMap<String,Integer> pastWorlds = new HashMap<String,Integer>();
                pastWorlds.put(w.toString(), generationStart);
                int currentGeneration = generationStart;
                int previousPopulation = w.getPopulation();
                while(true){
                        w = w.nextGeneration(0);
                        currentGeneration ++;
                        int currentPopulation = w.getPopulation();
                        maximumPopulation = Math.max(maximumPopulation, currentPopulation);
                        double change = ((double)currentPopulation - previousPopulation)/previousPopulation;
                        if(change > 0)
                                maximumGrowthRate = Math.max(maximumGrowthRate, change);
                        else
                                maximumDeathRate = Math.max(maximumDeathRate, -change);

                        if(pastWorlds.containsKey(w.toString())){
                                loopStart = pastWorlds.get(w.toString());
                                loopEnd = currentGeneration - 1;
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
}
