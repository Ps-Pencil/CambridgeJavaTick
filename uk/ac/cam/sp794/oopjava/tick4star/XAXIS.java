package uk.ac.cam.sp794.oopjava.tick4star;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.geom.Rectangle2D;
public class XAXIS extends JPanel{
        private Double minimum, maximum;
        public Dimension getPreferredSize() {
                return new Dimension(320,22);
        }
        protected void drawString(Graphics g, String text, int x, int y,int halign, int valign) {
                FontMetrics m = g.getFontMetrics();
                Rectangle2D r = m.getStringBounds(text, g);
                x -= r.getWidth() * halign / 2;
                y += r.getHeight() * valign / 2;
                g.drawString(text,x, y);
        }

        protected void paintComponent(Graphics g){
                super.paintComponent(g);
                g.setColor(java.awt.Color.BLACK);
                int width = getWidth();
                int height = getHeight();
                g.drawLine(51,0,width,0);
                drawString(g,String.format("%.1f",minimum),51,height/2,0,1);
                drawString(g,String.format("%.1f",maximum),width,height/2,2,1);

        }
        public void update(Double min, Double max){
                minimum = min;
                maximum = max;
                repaint();
        }

        public XAXIS(Double min, Double max){
                update(min,max);
        }
}
