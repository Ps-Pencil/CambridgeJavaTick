package uk.ac.cam.sp794.oopjava.tick3star;

import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;
import java.util.LinkedList;
import java.io.IOException;

import uk.ac.cam.acr31.life.World;
import uk.ac.cam.acr31.life.WorldViewer;
public class StatisticsLife{
        public static Statistics analyse(Pattern p) throws PatternFormatException{
                World world = new ArrayWorld(p.getWidth(),p.getHeight());  
                p.initialise(world);
                Statistics stat = new Statistics(world);
                return stat;
        }
        public static void main(String[] args) throws java.io.IOException {
                if (args.length == 0 || args.length > 1) {
                        System.out.println("StatisticsLife [patternSource]");
                } 
                else {
                        String patternSource = args[0];

                        List<Pattern> resultList = null;
                        if(patternSource.startsWith("http://")){
                                try{
                                        resultList = PatternLoader.loadFromURL(patternSource);
                                }
                                catch (IOException e){
                                        System.out.println("An error occurred loading from URL: "+patternSource);
                                        return;

                                }
                        }
                        else{
                                try{
                                        resultList = PatternLoader.loadFromDisk(patternSource);
                                }catch(IOException e){
                                        System.out.println("An error occurred loading from file: " + patternSource);
                                        return;
                                }
                        }

                        try{
                                String start="",cycle="",growth="",death="",pop="";
                                int longestStart = 0;
                                int longestCycle = 0;
                                double maximumGrowthRate = 0;
                                double maximumDeathrate = 0;
                                int maximumPopulation = 0;
                                for(Pattern p:resultList){
                                        System.out.println("Analysing "+p.getName());
                                        String name = p.getName();
                                        Statistics stat = analyse(p);
                                        if(stat.getLoopStart() > longestStart){
                                                longestStart = stat.getLoopStart();
                                                start = name;
                                        }
                                        if(stat.getLoopEnd()-stat.getLoopStart()+1 > longestCycle){
                                                longestCycle = stat.getLoopEnd()-stat.getLoopStart() + 1;
                                                cycle = name;
                                        }
                                        if(stat.getMaximumGrowthRate() > maximumGrowthRate){
                                                maximumGrowthRate = stat.getMaximumGrowthRate();
                                                growth = name;
                                        }
                                        if(stat.getMaximumDeathRate() > maximumDeathrate){
                                                maximumDeathrate = stat.getMaximumDeathRate();
                                                death = name;
                                        }
                                        if(stat.getMaximumPopulation() > maximumPopulation){
                                                maximumPopulation = stat.getMaximumPopulation();
                                                pop = name;
                                        }
                                }
                                System.out.println();
                                System.out.println("Longest start: "+start+" "+"("+longestStart+")");
                                System.out.println("Longest cycle: "+cycle+" "+"("+longestCycle+")");
                                System.out.println("Biggest growth rate: "+growth+" "+"("+maximumGrowthRate+")");
                                System.out.println("Biggest death rate: "+death+" "+"("+maximumDeathrate+")");
                                System.out.println("Largest population: "+pop+" "+"("+maximumPopulation+")");
                        }catch(PatternFormatException e){
                                System.out.println(e.getMessage());
                                return;
                        }

                }
        }

}



