package uk.ac.cam.sp794.oopjava.tick2;

import uk.ac.cam.acr31.life.World;

public class PackedWorld extends WorldImpl {
        private long cells;
        public PackedWorld(){
                super(8,8);
                cells = 0;
        }
        public PackedWorld(PackedWorld w){
                super(w);
                cells = 0;
        }
        public boolean getCell (int col, int row) {
                if(row<0||col<0|| row>getHeight()-1|| col>getWidth()-1)
                        return false;
                return PackedLong.get(cells,row*8+col);
        }
        public void setCell(int col, int row, boolean alive) {
                if(alive){
                        cells|= 1L<<(row*8+col);
                }else{
                        cells&=-1L-(1L<<(row*8+col));
                }
        }
        protected PackedWorld nextGeneration(){
                PackedWorld world = new PackedWorld(this);
                for(int i=0;i<getHeight();i++)
                        for(int j=0;j<getWidth();j++){
                                world.setCell(j,i,computeCell(j,i));
                        }
                return world;
        }
}
