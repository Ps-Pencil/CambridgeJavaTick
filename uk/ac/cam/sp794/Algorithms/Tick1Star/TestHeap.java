package uk.ac.cam.sp794.Algorithms.Tick1Star;
import java.util.ArrayList;
import java.util.List;
public class TestHeap{
        public static void main(String args[]){
                //List<Character> test_l = new ArrayList<Character>();
                //for(char c:"abcdefg".toCharArray()){
                        //test_l.add(new Character(c));
                //}
                //BottomUpMaxHeap<Character> test = new BottomUpMaxHeap<Character>(new ArrayList<Character>());
                //test.printList();
                char[] a = "azbycxdwevfugthsirjqkplomn".toCharArray();
                List<ComparisonCountingString> input = new ArrayList<ComparisonCountingString>();
                for(int i=0;i<a.length;i++){
                        input.add(new ComparisonCountingString(a[i]+""));
                        input.get(input.size()-1).resetComparisonCount();
                }

                MaxHeap<ComparisonCountingString> mh = new MaxHeap<ComparisonCountingString>(input);
                try{
                        mh.printList();
                        while(true)
                                System.out.println(mh.getMax()); // "Java"
                }catch(EmptyHeapException e){}
        }
}
