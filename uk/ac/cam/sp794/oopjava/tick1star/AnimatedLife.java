package uk.ac.cam.sp794.oopjava.tick1star;
import java.io.IOException;

public class AnimatedLife{
        public static void main(String[] args) throws java.io.IOException {
                Pattern p;
                boolean[][] world;
                int generationNo = Integer.parseInt(args[1]);
                String filename = args[2];
                try{
                        p = new Pattern(args[0]);
                        world = new boolean[p.getHeight()][p.getWidth()];
                        p.initialise(world);
                        play(world,generationNo,filename);
                }catch(PatternFormatException e){
                        System.out.println(e.getMessage());
                        return;
                }
        }


        public static String getPreferredPattern() {
                //TODO change this to return your chosen pattern specification
                //   return "Glider:Richard Guy:20:20:1:1:010 001 111";
                return "Infinited life:Pan Song:100:100:45:45:00000010 00001011 00001010 00001000 00100000 10100000";
        }
        public static int getPreferredGenerations() {
                return 100;
        }
        public static boolean getCell(boolean[][] world, int col, int row){
                col += world[0].length;
                col %= world[0].length;
                row += world.length;
                row %= world.length;
                return world[row][col]; 
        }
        public static void print(boolean[][] world,OutputAnimatedGif gif) throws IOException {
                //System.out.println("-");
                //for(int row=0;row<world.length;row++){
                //for(int col=0;col<world[row].length;col++){
                //System.out.print(getCell(world,col,row) ? "#":"_");
                //}
                //System.out.println();
                //}
                gif.addFrame(world);
        }
        public static void setCell(boolean[][] world, int col, int row, boolean value){
                world[row][col]=value;
        }
        public static long countNeighbours(boolean[][] world, int col, int row){
                int count = 0;
                count += (getCell(world, col-1,row-1)?1:0)+(getCell(world,col-1,row)?1:0)+(getCell(world,col-1,row+1)
                                ?1:0)+(getCell(world,col,row-1)?1:0)+(getCell(world,col,row+1)
                                        ?1:0)+(getCell(world,col+1,row-1)?1:0)+(getCell(world,col+1,row)?1:0)+(getCell(world,col+1,row+1)?1:0);
                return count;
        }
        public static boolean computeCell(boolean[][] world,int col,int row) {

                // liveCell is true if the cell at position (col,row) in world is live
                boolean liveCell = getCell(world, col, row);

                // neighbours is the number of live neighbours to cell (col,row)
                long neighbours = countNeighbours(world, col, row);

                // we will return this value at the end of the method to indicate whether 
                // cell (col,row) should be live in the next generation
                boolean nextCell = false;

                //A live cell with less than two neighbours dies (underpopulation)
                if (neighbours < 2) {
                        nextCell = false;
                }

                //A live cell with two or three neighbours lives (a balanced population)
                //TODO: write a if statement to check neighbours and update nextCell
                if (neighbours == 2 |neighbours == 3){
                        nextCell = liveCell;
                }
                //A live cell with with more than three neighbours dies (overcrowding)
                //TODO: write a if statement to check neighbours and update nextCell
                if (neighbours >3){
                        nextCell = false;
                }
                //A dead cell with exactly three live neighbours comes alive
                //TODO: write a if statement to check neighbours and update nextCell
                if (neighbours == 3 & !liveCell){
                        nextCell = true;
                }

                return nextCell;
        }  
        public static boolean[][] nextGeneration(boolean[][] world){
                boolean[][] new_world = new boolean[world.length][];
                for(int row=0;row<world.length;row++){
                        new_world[row] = new boolean[world[row].length];
                        for(int col=0;col<world[row].length;col++){
                                setCell(new_world,col,row,computeCell(world,col,row));
                        }
                }
                return new_world;
        }
        public static void play(boolean[][] world,int generationNo,String filename) throws java.io.IOException {
                OutputAnimatedGif gif = new OutputAnimatedGif(filename);
                for(int i = 0;i < generationNo;i++){
                        print(world,gif);
                        world = nextGeneration(world);
                }
        }

}


