package uk.ac.cam.sp794.oopjava.tick2; 

import uk.ac.cam.acr31.life.World;
import java.io.Writer;
import java.awt.Graphics;
import java.io.PrintWriter;

public class TestArrayWorld implements World {
        private int generation;
        private int width;
        private int height;
        private boolean[][] cells;

        public TestArrayWorld(int w, int h) {
                width = w;
                height = h;
                generation = 0;
                cells = new boolean[height][width];
        }
        protected TestArrayWorld(TestArrayWorld prev) {
                width = prev.width;
                height = prev.height;
                generation = prev.generation + 1;
                cells = new boolean[height][width];
        }

        public boolean getCell (int col, int row) {
                if(row<0||col<0|| row>height-1|| col>width-1)
                        return false;
                return cells[row][col];
        }
        public void setCell(int col, int row, boolean alive) {cells[row][col]=alive;}
        public int getWidth() {return width;}
        public int getHeight() {return height;}
        public int getGeneration() {return generation;}
        public int getPopulation() {return 0;}
        public void print(Writer w) {
                PrintWriter pw = new PrintWriter(w);

                pw.println("-");
                for(int i=0;i<height;i++){
                        for(int j=0;j<width;j++){
                                pw.print(getCell(j,i)?"#":"_");
                        }
                        pw.println();
                }
                pw.flush();

        }
        public void draw(Graphics g, int width, int height){}

        private TestArrayWorld nextGeneration(){
                TestArrayWorld world = new TestArrayWorld(this);
                for(int i=0;i<height;i++)
                        for(int j=0;j<width;j++){
                                world.setCell(j,i,computeCell(j,i));
                        }
                return world;
        }
        public World nextGeneration(int log2StepSize) {
                TestArrayWorld world = this;
                for(int i=0;i<(1<<log2StepSize);i++)
                        world=world.nextGeneration();
                return world;
        }

        private int countNeighbours(int col, int row){
                int count = 0;
                count += (getCell(col-1,row-1)?1:0)+(getCell(col-1,row)?1:0)+(getCell(col-1,row+1)?1:0)+(getCell(col,row-1)?1:0)+(getCell(col,row+1) ?1:0)+(getCell(col+1,row-1)?1:0)+(getCell(col+1,row)?1:0)+(getCell(col+1,row+1)?1:0);
                return count;
        }
        private boolean computeCell(int col, int row){
                boolean liveCell = getCell(col, row);
                long neighbours = countNeighbours(col, row);
                boolean nextCell = false;
                if (neighbours < 2) {
                        nextCell = false;
                }
                if (neighbours == 2 |neighbours == 3){
                        nextCell = liveCell;
                }
                if (neighbours >3){
                        nextCell = false;
                }
                if (neighbours == 3 & !liveCell){
                        nextCell = true;
                }
                return nextCell;
 
        }

}
