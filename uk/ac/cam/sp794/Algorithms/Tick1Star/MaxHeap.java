package uk.ac.cam.sp794.Algorithms.Tick1Star;
import java.util.List;
import java.util.ArrayList;
import uk.ac.cam.rkh23.Algorithms.Tick1.EmptyHeapException;
import uk.ac.cam.rkh23.Algorithms.Tick1Star.MaxHeapInterface;
public class MaxHeap<T extends Comparable<T>> implements MaxHeapInterface<T>{
        // the heap itself
        protected List<T> heap;
        public void printList(){
                for(T t:heap){
                        System.out.print(t);
                }
                System.out.println();
        }
        public MaxHeap(List<T> s){
                heap = new ArrayList<T>(s);
                heapify();
        }
        // helper methods
        protected static int leftChildren(int k){return (k<<1)+1;}
        protected static int rightChildren(int k){return (k<<1)+2;}
        protected static int parent(int k){
                if(k%2==0)
                        return (k-2)>>1;
                else
                        return (k-1)>>1;
        }
        protected void swap(int i,int j){T t=heap.get(i);heap.set(i,heap.get(j));heap.set(j,t);}

        // we could pre-test the heap to reduce function call overheads
        // convert the to heap
        private void heapify(){
                // start from the parent of of the last leaf
                for(int j=parent(heap.size());j>=0;j--){
                        normalise(j,findMax(j));
                }

        }
        public int getLength(){return heap.size();}
        // insert 
        @Override
        public void insert(T c){
                // put at end
                heap.add(c);
                // bubble up
                int cur = heap.size() - 1;
                int up = parent(cur);
                while(cur!=0 && findMax(up)!=up){
                        swap(up,cur);
                        cur = up;
                        up = parent(up);
                }
                
        }
        public T getMax() throws EmptyHeapException{
                if(heap.size() <= 0)
                        throw new EmptyHeapException();
                T max = heap.get(0);
                swap(0,heap.size()-1);
                heap.remove(heap.size()-1);
                normalise(0,findMax(0));
                return max;
        }

        // turn the sub-tree rooted at root into a heap
        private void normalise(int root,int maxIndex){
                // already a heap or reached the end
                if(root==maxIndex || root >= heap.size() || maxIndex >= heap.size())
                        return;
                else{
                        swap(root,maxIndex);
                        normalise(maxIndex,findMax(maxIndex));
                }
        }
        // return the maximum element's index 
        // also test if binary tree rooted at k is a heap
        private int findMax(int k){
                // leaf
                if(leftChildren(k)>=heap.size() 
                        && (rightChildren(k)>=heap.size() )){
                        return k;
                        }
                // only one children. compare with root
                else if(rightChildren(k)>=heap.size()){
                        return heap.get(leftChildren(k)).compareTo(heap.get(k))<0?k:leftChildren(k);
                }
                // two children
                else {
                        if(heap.get(k).compareTo(heap.get(leftChildren(k)))>0){
                                if(heap.get(k).compareTo(heap.get(rightChildren(k)))>0){
                                        return k;
                                }
                                else
                                        return rightChildren(k);
                        }
                        else{
                                if(heap.get(leftChildren(k)).compareTo(heap.get(rightChildren(k)))>0)
                                        return leftChildren(k);
                                else
                                        return rightChildren(k);
                        }
                }

        }
}
