package uk.ac.cam.sp794.Algorithms.Tick2;

public class TestLCS{
        public static void main(String[] args){
                LCSBottomUp lcs = new LCSBottomUp(args[0],args[1]);
                System.out.println(lcs.getLCSLength());
                lcs.debug();
                System.out.println(lcs.getLCSString());
        }
}
