// Is this control flow by Exception?
import java.util.LinkedList;
public class Grid implements Cloneable{
        private static String encode = "ENIGMA";
        private class NoSolution extends Exception{
                public NoSolution(){
                        super();
                }
                
        }
        private static final int SIZE = 6;
        private long[][] grid = new long[SIZE][SIZE];
        private boolean[][] fixed = new boolean[SIZE][SIZE];
        public Grid(){
                for(int i = 0; i<SIZE;i++){
                        for(int j=0;j<SIZE;j++){
                                grid[i][j] = (1<<SIZE)-1;
                                fixed[i][j] = false;
                        }
                }
        }
        @Override
        public Grid clone() throws CloneNotSupportedException{
                Grid cloned = (Grid)super.clone();
                cloned.grid = new long[SIZE][SIZE];
                for(int i=0;i<SIZE;i++){
                        for(int j=0;j<SIZE;j++)
                                cloned.grid[i][j] = this.grid[i][j];
                }
                cloned.fixed = new boolean[SIZE][SIZE];
                
                for(int i=0;i<SIZE;i++){
                        for(int j=0;j<SIZE;j++)
                                cloned.fixed[i][j] = this.fixed[i][j];
                }
                return cloned;
        }
        public void setCell(int row,int col, long n) throws NoSolution{
                for(int i=0;i<SIZE;i++){
                        grid[row][i] &= -1 - n;
                        if(i!=col && grid[row][i] == 0)
                                throw new NoSolution();
                        grid[i][col] &= -1 - n;
                        if(i!=row && grid[i][col] == 0)
                                throw new NoSolution();
                        if(col == row){
                                grid[i][i] &= -1 - n;
                                if(i!=row && grid[i][col] == 0)
                                        throw new NoSolution();
                        }
                }
                grid[row][col] = n;
                fixed[row][col] = true;
        }
        public void solve() throws NoSolution{
                for(int i=0;i<SIZE;i++){
                        for(int j=0;j<SIZE;j++){
                                if(!fixed[i][j] && grid[i][j]-(grid[i][j]&(-grid[i][j]))==0){
                                        setCell(i,j,grid[i][j]);
                                        i = 0;
                                        j = -1;
                                }
                        }
                }

        }
        public boolean solved(){
                for(int i=0;i<SIZE;i++)
                        for(int j=0;j<SIZE;j++)
                                if(!fixed[i][j])
                                        return false;
                return true;
        }
        
        public void display(){
                char[] decode = new char[(1<<SIZE)+1];
                for(int i=0;i<SIZE;i++)
                        decode[1<<i] = encode.toCharArray()[i];
                for(int i=0;i<SIZE;i++){
                        for(int j=0;j<SIZE;j++)
                                System.out.print(decode[(int)grid[i][j]]+" ");
                        System.out.println();
                }
                System.out.println();
        }
        public long nextCell(){
                long lowest = SIZE;
                int li=-1,lj=-1;
                for(int i=0;i<SIZE;i++){
                        for(int j=0;j<SIZE;j++){
                                if(!fixed[i][j]){
                                        long x = grid[i][j];
                                        int c = 0;
                                        while(x!=0){
                                                x-=x&(-x);
                                                c++;
                                        }
                                        if(c<lowest){
                                                lowest = c;
                                                li = i;
                                                lj = j;
                                        }
                                }
                        }
                }
                return li * SIZE + lj;
        }
        public static void main(String[] args){
                Grid g = new Grid();
                try{
                        g.setCell(3,0,1<<5);
                        g.setCell(4,0,1<<1);
                        g.setCell(5,0,1<<0);
                        g.setCell(5,1,1<<1);
                        g.setCell(5,2,1<<2);
                        g.setCell(5,3,1<<3);
                        g.setCell(5,4,1<<4);
                        g.setCell(5,5,1<<5);
                        g.setCell(4,5,1<<4);
                        g.setCell(3,5,1<<0);
                        g.setCell(2,5,1<<3);
                }catch(NoSolution e){}
                LinkedList<Grid> q = new LinkedList<Grid>();
                q.add(g);
                int h = 0;
                while(!q.isEmpty()){
                        Grid x = q.remove();
                        try{
                                x.solve();
                                if(x.solved()){
                                        x.display();
                                        break;
                                }
                                long tmp = x.nextCell();
                                int row = (int)(tmp/SIZE);
                                int col = (int)(tmp%SIZE);
                                tmp = x.grid[row][col];
                                while(tmp!=0){
                                        try{
                                                Grid cloned = x.clone();
                                                cloned.setCell(row,col,tmp&(-tmp));
                                                q.add(cloned);
                                        }catch(CloneNotSupportedException e){}
                                        catch(NoSolution e){}
                                        finally{
                                                tmp -= tmp&(-tmp);
                                        }
                                }
                        }catch(NoSolution e){}
                }

        }
}
