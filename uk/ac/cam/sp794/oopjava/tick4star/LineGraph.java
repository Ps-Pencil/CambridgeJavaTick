package uk.ac.cam.sp794.oopjava.tick4star;

import javax.swing.JPanel;
import java.util.List;
import java.awt.Graphics;
import java.util.Collections;
public class LineGraph extends JPanel{
        private List<Double> data;
        private Double span;
        private Double min;
        public static int resize(Double total, Double point,int length, int xy){
                if(xy == 0) //x direction
                        return (int)(point/total*length);
                else //y direction
                        return (int)(length - point/total*length);
        }
        protected void paintComponent(Graphics g){
                if(data == null) return;
                super.paintComponent(g);
                int width = getWidth();
                int height = getHeight();
                g.setColor(java.awt.Color.WHITE);
                g.fillRect(0,0,width,height);
                g.setColor(java.awt.Color.RED);
                int generation = -1;
                int prevX = -1;
                int prevY = -1;
                for(Double y : data){
                        if(generation == -1){
                                prevX = 0;
                                prevY = resize(span,y-min,height,1);
                                generation = 1;
                                continue;
                        }
                        int currentX = resize(100.0,(double)generation,width,0);
                        int currentY = resize(span,y-min,height,1);
                        g.drawLine(prevX,prevY,currentX,currentY);
                        prevX = currentX;
                        prevY = currentY;
                        generation ++;
                }

        }
        public void update(List<Double> d){
                if(d.size() == 0)
                        return;
                data = d;
                span = Collections.max(d)-Collections.min(d);
                min = Collections.min(d);
                repaint();
        }
        public LineGraph(){

        }

}
