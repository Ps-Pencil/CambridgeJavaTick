package uk.ac.cam.sp794.oopjava.tick4;

import uk.ac.cam.acr31.life.World;
public class Pattern {

        private String name;
        private String author;
        private int width;
        private int height;
        private int startCol;
        private int startRow;
        private String cells;
        //TODO: write public 'get' methods for EACH of the fields above;
        //      for instance 'getName' should be written as:
        public String getName() {
                return name;
        }
        public String getAuthor() {
                return author;
        }
        public int getWidth() {
                return width;
        }
        public int getHeight() {
                return height;
        }
        public int getStartCol() {
                return startCol;
        }
        public int getStartRow() {
                return startRow;
        }
        public String getCells() {
                return cells;
        }

        public Pattern(String format) throws PatternFormatException {
                //TODO: initialise all fields of this class using contents of 
                //      'format' to determine the correct values.
                if(format==""){
                        throw new PatternFormatException("Please specify a pattern.");
                }
                if(format.split(":").length!=7){
                        throw new PatternFormatException("Invalid pattern format: Incorrect number of fields in Pattern (found "+format.split(":").length+").");
                }
                String[] fields = format.split(":");
                name = fields[0];
                author = fields[1];
                try{
                        width = Integer.parseInt(fields[2]);
                }catch(NumberFormatException e){
                        throw new PatternFormatException("Invalid pattern format: Couldn't interpret the width field as a number (\""+fields[2]+"\" given).");
                }
                try{
                        height = Integer.parseInt(fields[3]);
                }catch(NumberFormatException e){
                        throw new PatternFormatException("Invalid pattern format: Couldn't interpret the height field as a number (\""+fields[3]+"\" given).");
                }

                try{
                        startCol = Integer.parseInt(fields[4]);
                }catch(NumberFormatException e){
                        throw new PatternFormatException("Invalid pattern format: Couldn't interpret the startX field as a number (\""+fields[4]+"\" given).");
                }
                try{
                        startRow = Integer.parseInt(fields[5]);
                }catch(NumberFormatException e){
                        throw new PatternFormatException("Invalid pattern format: Couldn't interpret the startY field as a number (\'"+fields[5]+"\' given).");
                }
                cells = fields[6];
        }

        public void initialise(World w) throws PatternFormatException{
                //TODO: update the values in the 2D array representing the state of
                //      'world' as expressed by the contents of the field 'cells'.
                for(int i = 0;i<cells.split(" ").length;i++){
                        String row = cells.split(" ")[i];
                        for(int j = 0;j< row.toCharArray().length;j++){
                                int digit = row.toCharArray()[j] - '0';
                                if(digit==1){
                                        w.setCell(j+startCol,i+startRow, true);
                                }
                                if(digit<0 || digit >1){
                                        throw new PatternFormatException("Invalid pattern format: Malformed pattern \'"+cells+ "\'.");
                                }
                        }
                }
        }
} 
