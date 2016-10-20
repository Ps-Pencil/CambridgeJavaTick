package uk.ac.cam.sp794.oopjava.tick5star;
import uk.ac.cam.acr31.life.World;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import java.awt.Dimension;
import java.awt.Graphics;
public abstract class GamePanel extends JPanel {
        protected int zoom = 10; //Number of pixels used to represent a cell
        protected int width = 1; //Width of game board in pixels
        protected int height = 1;//Height of game board in pixels
        protected World current = null;
        protected abstract void onWorldChanged();
        public World getCurrentWorld(){return current;}
        public void advance(int step){
                for(int i=0;i<step;i++)
                        current = current.nextGeneration(0);
                repaint();
        }
        public GamePanel(){
                super();
                this.addMouseListener(new MouseAdapter(){ 
                        public void mousePressed(MouseEvent me) { 
                                java.awt.Point p = me.getPoint();
                                int col = p.x/zoom;
                                int row = p.y/zoom;
                                ((ArrayWorld)current).toggleCell(col,row);
                                display(current);
                                onWorldChanged();
                        } 
                });

        }
        public Dimension getPreferredSize() {
                return new Dimension(width, height);
        }
        protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (current == null) return;
                g.setColor(java.awt.Color.WHITE);
                g.fillRect(0, 0, width, height);
                current.draw(g, width, height);
                if (zoom > 4) {
                        g.setColor(java.awt.Color.LIGHT_GRAY);
                        //TODO: Using for loops call the drawLine method on "g",
                        // repeatedly to draw a grid of grey lines to delimit
                        // the border of the cells in the game board 
                        for (int x = 0; x <= width; x += zoom) {
                                g.drawLine(x, 0, x, height);
                        }

                        for (int y = 0; y <= height; y += zoom) {
                                g.drawLine(0, y, width, y);
                        }
                        
                }
        }
        protected void computeSize() {
                if (current == null) return;
                int newWidth = current.getWidth() * zoom;
                int newHeight = current.getHeight() * zoom;
                if (newWidth != width || newHeight != height) {
                        width = newWidth;
                        height = newHeight;
                        revalidate(); //trigger the GamePanel to re-layout its components
                }
        }
        public void sync(Pattern p) throws PatternFormatException {
                // if the new world and old world are the same in dimension, could have this removed
                if(p == null) return;
                current = new ArrayWorld(p.getWidth(),p.getHeight());
                p.initialise(current);
                display(current);
        }
        public void display(World w) {
                current = w;
                computeSize();
                repaint();
        }
}

