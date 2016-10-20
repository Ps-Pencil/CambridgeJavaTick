package uk.ac.cam.sp794.oopjava.tick4;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.Writer;
import java.awt.Graphics;
import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOInvalidTreeException;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.FileImageOutputStream;
import uk.ac.cam.acr31.life.World;


public abstract class WorldImpl implements World {
        private int width;
        private int height;
        private int generation;
        protected WorldImpl (int width, int height){
                this.width = width;
                this.height = height;
                this.generation = 0;
        }
        protected WorldImpl (WorldImpl prev){
                this.width = prev.width;
                this.height = prev.height;
                this.generation = prev.generation+1;
        }
        public int getWidth(){return this.width;}
        public int getHeight() {return this.height;}
        public int getGeneration() {return this.generation;}
        public int getPopulation() {return 0;}
        protected String getCellAsString(int col,int row){
                return getCell(col,row)? "#":"_";
        }
        protected Color getCellAsColour(int col, int row){
                return getCell(col,row)? Color.BLACK : Color.WHITE;
        }

        public void draw(Graphics g, int width, int height){
                int worldWidth = getWidth();
                int worldHeight = getHeight();

                double colScale = (double)width/(double)worldWidth;
                double rowScale = (double)height/(double)worldHeight;

                for(int col=0;col<worldWidth;col++){
                        for(int row=0;row<worldHeight;row++){
                                int colPos = (int)(col*colScale);
                                int rowPos = (int)(row*rowScale);
                                int nextCol = (int)((col+1)*colScale);
                                int nextRow = (int)((row+1)*rowScale);

                                if(g.hitClip(colPos,rowPos,nextCol-colPos,nextRow-rowPos)){
                                        g.setColor(getCellAsColour(col,row));
                                        g.fillRect(colPos,rowPos,nextCol-colPos,nextRow-rowPos);
                                }
                        }
                }

        }
        public World nextGeneration(int log2StepSize){
                WorldImpl w = this;
                for(int i=0;i<(1<<log2StepSize);i++){
                        w = w.nextGeneration();
                }
                return w;
        }
        public void print(Writer w) {
                PrintWriter pw = new PrintWriter(w);

                pw.println("-");
                for(int i=0;i<height;i++){
                        for(int j=0;j<width;j++){
                                pw.print(getCellAsString(j,i));
                        }
                        pw.println();
                }
                pw.flush();

        }
        protected int countNeighbours(int col, int row){
                int count = 0;
                count += (getCell(col-1,row-1)?1:0)+(getCell(col-1,row)?1:0)+(getCell(col-1,row+1)?1:0)+(getCell(col,row-1)?1:0)+(getCell(col,row+1) ?1:0)+(getCell(col+1,row-1)?1:0)+(getCell(col+1,row)?1:0)+(getCell(col+1,row+1)?1:0);
                return count;
        }
        protected  boolean computeCell(int col, int row){
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
        public abstract boolean getCell(int col, int row);
        public abstract void setCell(int col, int row, boolean alive);
        protected abstract WorldImpl nextGeneration();
 
 
}
