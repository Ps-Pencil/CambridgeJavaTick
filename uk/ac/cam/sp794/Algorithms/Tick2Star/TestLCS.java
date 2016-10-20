package uk.ac.cam.sp794.Algorithms.Tick2Star;

public class TestLCS{
        public static void main(String[] args){
                LCSTopDownNonRecursive lcs = new LCSTopDownNonRecursive(args[0],args[1]);
                System.out.println(lcs.getLCSLength());
                System.out.println(lcs.getLCSString());
        }
}
