package uk.ac.cam.sp794.oopjava.tick4star;

import java.util.List;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.Collections;

public class Graph extends JPanel{
        private String title;
        private JLabel heading;
        private List<Double> data;
        private XAXIS x;
        private YAXIS y;
        private LineGraph graph;

        public void draw(){
                if(data.size() == 0){
                        return;
                }
                Double max = Collections.max(data);
                Double min = Collections.min(data);
                y.update(min,max);
                graph.update(data);
        }
        public void update(List<Double> d){
                data = d;
                if(data.size() == 0)
                        System.out.println("Empty");
                draw();
                repaint();
        }

        public Graph(String t, List<Double> d){
                super();
                setLayout(new BorderLayout());
                title = t;
                data = d;

                heading = new JLabel(title);
                add(heading, BorderLayout.NORTH);
                
                x = new XAXIS(0.0,100.0);
                add(x,BorderLayout.SOUTH);

                y = new YAXIS(0.0,100.0);
                add(y,BorderLayout.WEST);

                graph = new LineGraph();
                add(graph,BorderLayout.CENTER);

                draw();
        }
}
