package uk.ac.cam.sp794.Algorithms.Tick3;
import uk.ac.cam.rkh23.Algorithms.Tick3.TargetUnreachable; 
import java.io.IOException;
public class TestGraph{
    public static void main(String args[]){
        int test[][] = new int[5][5];
        test[0][1] = 1;
        test[0][2] = 1;
        test[1][2] = 1;
        test[2][3] = 4;
        test[3][4] = 4;
        test[2][4] = 5;
        try{
            Graph g = new Graph(test);
            System.out.println("Hello");
            for(Integer i: g.getFewestEdgesPath(0,4))
            System.out.println(i);
        }catch(TargetUnreachable e){}
        catch(IOException e){}
    }
}
