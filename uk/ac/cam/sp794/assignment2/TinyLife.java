package uk.ac.cam.sp794.assignment2;

public class TinyLife{
    public static void main(String[] args)throws java.io.IOException{
//        long world = 0x20A0600000000000L;
  //      System.out.println(getCell(world,-1,1));
 //       print(world);
//        print(setCell(0,1,1,true));
//        System.out.println(countNeighbours(0x20A0600000000000L,6,6));
        System.out.println(computeCell(0x20A0600000000000L,4,6));
        play(Long.decode(args[0]));
    }
    public static boolean getCell(long world, int col, int row){
        if(col>7 | row>7|row<0|col<0)
            return false;
        return PackedLong.get(world,row*8+col);
    }
    public static void print(long world){
        System.out.println("-");
        for(int row=0;row<8;row++){
            for(int col=0;col<8;col++){
                System.out.print(getCell(world,col,row) ? "#":"_");
            }
            System.out.println();
        }
    }
    public static long setCell(long world, int col, int row, boolean value){
        if(value){
            world|= 1L<<(row*8+col);
        }else{
            world&=-1L-(1L<<(row*8+col));
        }
        return world;
    }
    public static long countNeighbours(long world, int col, int row){
       int count = 0;
       count += (getCell(world, col-1,row-1)?1:0)+(getCell(world,col-1,row)?1:0)+(getCell(world,col-1,row+1)
           ?1:0)+(getCell(world,col,row-1)?1:0)+(getCell(world,col,row+1)
           ?1:0)+(getCell(world,col+1,row-1)?1:0)+(getCell(world,col+1,row)?1:0)+(getCell(world,col+1,row+1)?1:0);
       return count;
    }
    public static boolean computeCell(long world,int col,int row) {

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
    public static long nextGeneration(long world){
        long new_world = 0;
        for(int row=0;row<8;row++)
            for(int col=0;col<8;col++){
                new_world += (computeCell(world,col,row)?1L:0L)<<(row*8+col);
            }
        return new_world;
    }
    public static void play(long world) throws java.io.IOException {
        int userResponse = 0;
        while(userResponse!='q'){
            print(world);
            userResponse = System.in.read();
            world = nextGeneration(world);
        }
    }

}
    

