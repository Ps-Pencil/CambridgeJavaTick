package uk.ac.cam.sp794.oopjava.tick3;

import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;
import java.util.LinkedList;
import java.io.IOException;

import uk.ac.cam.acr31.life.World;
import uk.ac.cam.acr31.life.WorldViewer;
public class LoaderLife{
        public static void main(String[] args) throws java.io.IOException {
                if (args.length == 0) {
                        System.out.println("LoaderLife [optional worldType] [patternSource] [option patternIndex]");
                } 
                else {
                        String worldType = null;
                        String patternSource = null;
                        String patternIndex = null;
                        int haveType = 0;
                        if(args[0].startsWith("--")){
                                worldType = args[0];
                                haveType = 1;
                        }else
                                worldType = "--array";

                        if(args.length >= 1 + haveType)
                                patternSource = args[0+haveType];
                        if(args.length >= 2 + haveType)
                                patternIndex = args[1 + haveType];

                        if(patternSource == null){
                                System.out.println("Please specify a pattern source.");
                        } 
                        else {
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

                                if(patternIndex == null){

                                        int index = 0;
                                        for(Pattern p : resultList){
                                                System.out.println(Integer.toString(index) + "\t" + p.getName() + "\t" + p.getAuthor());
                                                index++;
                                        }
                                }
                                else {
                                        int patternIndexInt = 0;
                                        try{
                                                patternIndexInt = Integer.parseInt(patternIndex);

                                                Pattern p;
                                                try{
                                                        p = resultList.get(patternIndexInt);
                                                }catch(IndexOutOfBoundsException e){
                                                        System.out.println("Chosen index number not present in list."); 
                                                        return;
                                                }
                                                World world;
                                                try{
                                                        if(worldType.equals("--long"))
                                                                world = new PackedWorld();
                                                        else if(worldType.equals("--array"))
                                                                world = new ArrayWorld(p.getWidth(),p.getHeight());
                                                        else if(worldType.equals("--aging"))
                                                                world = new AgingWorld(p.getWidth(), p.getHeight());
                                                        else {
                                                                // or aging
                                                                System.out.println("Unrecognised world type. Use '--long' or '--array'"); 
                                                                return;
                                                        }
                                                        p.initialise(world);
                                                        play(world);
                                                }catch(PatternFormatException e){
                                                        System.out.println(e.getMessage());
                                                        return;
                                                }catch(ArrayIndexOutOfBoundsException e){
                                                        System.out.println("Please specify a pattern.");
                                                }

                                        } catch(NumberFormatException e){
                                                System.out.println("Could not interpret patternIndex as an integer (given '"+patternIndex+"').");
                                                return;
                                        }
                                }

                        }
                }
        }

        //public static boolean getCell(boolean[][] world, int col, int row){
                //if(row<0||col<0|| row>world.length-1|| col>world[row].length-1)
                        //return false;
                //return world[row][col]; 
        //}
        //public static void print(boolean[][] world){
                //System.out.println("-");
                //for(int row=0;row<world.length;row++){
                        //for(int col=0;col<world[row].length;col++){
                                //System.out.print(getCell(world,col,row) ? "#":"_");
                        //}
                        //System.out.println();
                //}
        //}
        //public static void setCell(boolean[][] world, int col, int row, boolean value){
                //world[row][col]=value;
        //}
        //public static long countNeighbours(boolean[][] world, int col, int row){
                //int count = 0;
                //count += (getCell(world, col-1,row-1)?1:0)+(getCell(world,col-1,row)?1:0)+(getCell(world,col-1,row+1)
                                //?1:0)+(getCell(world,col,row-1)?1:0)+(getCell(world,col,row+1)
                                        //?1:0)+(getCell(world,col+1,row-1)?1:0)+(getCell(world,col+1,row)?1:0)+(getCell(world,col+1,row+1)?1:0);
                //return count;
        //}
        //public static boolean computeCell(boolean[][] world,int col,int row) {

                //// liveCell is true if the cell at position (col,row) in world is live
                //boolean liveCell = getCell(world, col, row);

                //// neighbours is the number of live neighbours to cell (col,row)
                //long neighbours = countNeighbours(world, col, row);

                //// we will return this value at the end of the method to indicate whether 
                //// cell (col,row) should be live in the next generation
                //boolean nextCell = false;

                ////A live cell with less than two neighbours dies (underpopulation)
                //if (neighbours < 2) {
                        //nextCell = false;
                //}

                ////A live cell with two or three neighbours lives (a balanced population)
                ////TODO: write a if statement to check neighbours and update nextCell
                //if (neighbours == 2 |neighbours == 3){
                        //nextCell = liveCell;
                //}
                ////A live cell with with more than three neighbours dies (overcrowding)
                ////TODO: write a if statement to check neighbours and update nextCell
                //if (neighbours >3){
                        //nextCell = false;
                //}
                ////A dead cell with exactly three live neighbours comes alive
                ////TODO: write a if statement to check neighbours and update nextCell
                //if (neighbours == 3 & !liveCell){
                        //nextCell = true;
                //}

                //return nextCell;
        //}  
        //public static boolean[][] nextGeneration(boolean[][] world){
                //boolean[][] new_world = new boolean[world.length][];
                //for(int row=0;row<world.length;row++){
                        //new_world[row] = new boolean[world[row].length];
                        //for(int col=0;col<world[row].length;col++){
                                //setCell(new_world,col,row,computeCell(world,col,row));
                        //}
                //}
                //return new_world;
        //}
        public static void play(World world) throws java.io.IOException {
                int userResponse = 0;
                Writer w = new OutputStreamWriter(System.out);

                WorldViewer viewer = new WorldViewer();
                
                while(userResponse!='q'){
                        world.print(w);
                        viewer.show(world);
                        userResponse = System.in.read();
                        world = world.nextGeneration(0);
                }
                viewer.close();
        }

}


