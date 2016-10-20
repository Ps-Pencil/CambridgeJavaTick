package uk.ac.cam.sp794.Algorithms.Tick1;
public class TestHeap{
        public static void main(String args[]){
                try{
                        MaxCharHeap heap = new MaxCharHeap(args[0]);
                        MaxCharHeap heap2 = new MaxCharHeap("");
                        for(int i=0;i<args[0].length();i++)
                                heap2.insert(args[0].toCharArray()[i]);
                        while(true){
                                System.out.println(heap.getMax());
                                System.out.println("2 "+heap2.getMax());
                        }
                } catch(EmptyHeapException e){

                }
        }
}
