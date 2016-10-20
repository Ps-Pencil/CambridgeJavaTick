package uk.ac.cam.sp794.oopjava.tick5star;

import javax.swing.JPanel;
import uk.ac.cam.acr31.life.World;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import java.util.List;
import java.util.ArrayList;
import javax.swing.Box;
public abstract class GenerationPanel extends JPanel{
        private World world = null;
        private List<Thumbnail> panels;
        public Dimension getPreferredSize() {
                return new Dimension(2000,100);
        }
        protected abstract void step(World w);
        public void sync(World w){
                if(w == null) return;
                for(int i=0;i<20;i++){
                        Thumbnail x = panels.get(i);
                        x.display(w);
                        w = w.nextGeneration(0);
                }
        }
        public GenerationPanel(World w){
                super();
                setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
                world = w;
                panels = new ArrayList<Thumbnail>();
                for(int i=0;i<20;i++){
                        Thumbnail x = new Thumbnail(w){
                                protected void advance(){
                                        step(getCurrentWorld());
                                } 
                        };
                        add(x);
                        if(i!=19)
                        add(Box.createHorizontalStrut(10)); //add 10px of extra space
                        panels.add(x);
                        w = w.nextGeneration(0);

                }
        }
        

}
