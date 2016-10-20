package uk.ac.cam.sp794.oopjava.tick4star;

import uk.ac.cam.acr31.life.World;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JLabel;
import java.util.ArrayList;
import java.awt.Dimension;

public class StatisticsPanel extends JPanel{

        private Statistics stat = null;
        private Box info;
        private Box graphs;
        private Graph population, change, rate;
        public Dimension getPreferredSize() {
                return new Dimension(320,480);
        }

        // Here, update by creating new ones. If to minimise memory usage, could keep track of each label and create in the constructor
        public void updateLabels(){
                if(stat == null) return;

                info = Box.createVerticalBox();

                info.add(new JLabel(Strings.STATISTICS_MAXIMUM_POPULATION + ": " + stat.getMaximumPopulation()));
                info.add(new JLabel(Strings.STATISTICS_MINIMUM_POPULATION + ": " + stat.getMinimumPopulation()));
                info.add(new JLabel(Strings.STATISTICS_MAXIMUM_GROWTH_RATE + ": "+ String.format("%.4f",stat.getMaximumGrowthRate())));
                info.add(new JLabel(Strings.STATISTICS_MAXIMUM_DEATH_RATE + ": " + String.format("%.4f",stat.getMaximumDeathRate())));
                add(info,BorderLayout.NORTH);
        }
        public void updateGraphs(){
                if(stat == null) return;
                population.update(stat.getPopulations());
                change.update(stat.getChanges());
                rate.update(stat.getRates());

        }
        public void createGraphs(){
                population = new Graph(Strings.STATISTICS_POPULATION, new ArrayList<Double>(100));
                change = new Graph(Strings.STATISTICS_POPULATION_CHANGE, new ArrayList<Double>(100));
                rate = new Graph(Strings.STATISTICS_CHANGE_RATE, new ArrayList<Double>(100));
                graphs.add(Box.createVerticalStrut(10));
                graphs.add(population);
                graphs.add(Box.createVerticalStrut(10));
                graphs.add(change);
                graphs.add(Box.createVerticalStrut(10));
                graphs.add(rate);
        }
        public StatisticsPanel(){
                super();
                setLayout(new BorderLayout());

                info = Box.createVerticalBox();
                add(info,BorderLayout.NORTH);
                
                graphs = Box.createVerticalBox();
                add(graphs,BorderLayout.CENTER);
                createGraphs();
        }
        public void update(World w){
               stat = new Statistics(w);
               updateLabels();
               updateGraphs();
        }
}
