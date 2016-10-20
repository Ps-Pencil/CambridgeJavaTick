package uk.ac.cam.sp794.Algorithms.Tick1Star;
import java.util.List;
import uk.ac.cam.rkh23.Algorithms.Tick1.EmptyHeapException;
import uk.ac.cam.rkh23.Algorithms.Tick1Star.MaxHeapInterface;
public class BottomUpMaxHeap<T extends Comparable<T>> extends MaxHeap<T>{
        public BottomUpMaxHeap(List<T> l){super(l);}
        @Override
        public T getMax() throws EmptyHeapException{
                if(heap.size()==0) throw new EmptyHeapException();
                // swap root and end
                swap(0,heap.size()-1);
                // store max
                T max = heap.get(heap.size()-1);
                heap.remove(heap.size()-1);
                if(heap.size()==0) return max;
                // temporary variable for root
                T temp = heap.get(0);
                // going down
                int index = 0;
                // while still parent
                while(true){
                        //System.out.println(index);
                        // leaf
                        if(leftChildren(index)>=heap.size() && rightChildren(index)>=heap.size())
                                break;
                        // only one children
                        if(rightChildren(index)>=heap.size()) {
                                index = leftChildren(index);
                                break;
                        }
                        // go for the bigger
                        index = heap.get(leftChildren(index)).compareTo(heap.get(rightChildren(index))) < 0 ? rightChildren(index) : leftChildren(index);
                }
                // while smaller than temp, go up
                while(index!=0 && heap.get(index).compareTo(temp)<=0){
                        index = parent(index);
                }
                // memorise this location
                int p = index;
                // continue walking up pushing up elements

                T t = heap.get(index);
                while(index!=0){
                        T tt = heap.get(parent(index));
                        heap.set(parent(index),t);
                        t = tt;
                        index = parent(index);
                }
                heap.set(p,temp);
                return max;

        }
}
