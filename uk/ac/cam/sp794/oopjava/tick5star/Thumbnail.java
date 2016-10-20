package uk.ac.cam.sp794.oopjava.tick5star;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import uk.ac.cam.acr31.life.World;
import java.awt.event.MouseListener;
public abstract class Thumbnail extends GamePanel{
        protected void onWorldChanged(){}
        protected abstract void advance();
        private void computeZoom(){
               this.zoom = 1; 
               if(this.current.getWidth()*this.zoom < 70 || this.current.getHeight()*this.zoom < 70 ){
                       this.zoom = Math.min(70/this.current.getWidth()+1,70/this.current.getHeight()+1);
               }
               
        }
        @Override
        public void display(World w){
                computeZoom();
                super.display(w);
        }
        public Thumbnail(World w){
                super();
                for(MouseListener m : this.getMouseListeners()){
                        removeMouseListener(m);
                }
                addMouseListener(new MouseAdapter(){
                        public void mousePressed(MouseEvent me){
                                advance();
                        }

                });
                this.current = w;
                computeZoom();
                display(w);
        }
}
