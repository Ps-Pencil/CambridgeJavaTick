package uk.ac.cam.sp794.oopjava.tick1;

public class PatternLife{
        public static void main(String[] args) throws java.io.IOException {
                Pattern p;
                boolean[][] world;
                try{
                        p = new Pattern(args[0]);
                        world = new boolean[p.getHeight()][p.getWidth()];
                        p.initialise(world);
                        play(world);
                }catch(PatternFormatException e){
                        System.out.println(e.getMessage());
                        return;
                }catch(ArrayIndexOutOfBoundsException e){
                        System.out.println("Please specify a pattern.");
                }
        }

        public static boolean getCell(boolean[][] world, int col, int row){
                if(row<0||col<0|| row>world.length-1|| col>world[row].length-1)
                        return false;
                return world[row][col]; 
        }
        public static void print(boolean[][] world){
                System.out.println("-");
                for(int row=0;row<world.length;row++){
                        for(int col=0;col<world[row].length;col++){
                                System.out.print(getCell(world,col,row) ? "#":"_");
                        }
                        System.out.println();
                }
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
        public static void play(boolean[][] world) throws java.io.IOException {
                int userResponse = 0;
                while(userResponse!='q'){
                        print(world);
                        userResponse = System.in.read();
                        world = nextGeneration(world);
                }
        }

}


