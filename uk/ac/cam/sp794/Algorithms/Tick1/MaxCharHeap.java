package uk.ac.cam.sp794.Algorithms.Tick1;
import uk.ac.cam.rkh23.Algorithms.Tick1.MaxCharHeapInterface;
import uk.ac.cam.rkh23.Algorithms.Tick1.EmptyHeapException;
public class MaxCharHeap implements MaxCharHeapInterface{
        // the heap itself
        private char[] heap;
        private int size;
        private int last;
        public MaxCharHeap(String s){
                size = s.length();
                last = size;
                heap = s.toLowerCase().toCharArray();

                heapify();
        }
        // helper methods
        private static int leftChildren(int k){return (k<<1)+1;}
        private static int rightChildren(int k){return (k<<1)+2;}
        private static int parent(int k){
                if(k%2==0)
                        return (k-2)>>1;
                else
                        return (k-1)>>1;
        }
        private void swap(int i,int j){char t=heap[i];heap[i]=heap[j];heap[j]=t;}

        // we could pre-test the heap to reduce function call overheads
        // convert the char[] to heap
        private void heapify(){
                // start from the parent of of the last leaf
                for(int j=parent(last);j>=0;j--){
                        normalise(j,findMax(j));
                }

        }
        public int getLength(){return last;}
        // expand the array
        private void expand(){
                int new_size = size<<1;
                if(new_size == 0)
                        new_size = 10;
                char[] new_array = new char[new_size];
                for(int i=0;i<size;i++)
                        new_array[i] = heap[i];
                heap = new_array;
        }
        // insert 
        public void insert(char c){
                // convert to lowercase
                if(c>='A' && c<='Z')
                        c = (char)(c -'A'+'a');
                // if full
                if(last==size)
                        expand();
                // put at end
                heap[last++] = c;
                // bubble up
                int cur = last - 1;
                int up = parent(cur);
                while(cur!=0 && findMax(up)!=up){
                        swap(up,cur);
                        cur = up;
                        up = parent(up);
                }
                
        }
        public char getMax() throws EmptyHeapException{
                if(last <= 0)
                        throw new EmptyHeapException();
                char max = heap[0];
                swap(0,last-1);
                last--;
                normalise(0,findMax(0));
                return max;
        }

        // turn the sub-tree rooted at root into a heap
        private void normalise(int root,int maxIndex){
                // already a heap or reached the end
                if(root==maxIndex || root >= last || maxIndex >= last)
                        return;
                else{
                        swap(root,maxIndex);
                        normalise(maxIndex,findMax(maxIndex));
                }
        }
        // return the maximum element's index 
        // also test if binary tree rooted at k is a heap
        private int findMax(int k){
                // already a binary heap
                if((leftChildren(k)>=last || leftChildren(k)<last && heap[leftChildren(k)] < heap[k]) 
                        && (rightChildren(k)>=last || rightChildren(k)<last && heap[rightChildren(k)] < heap[k])){
                        return k;
                        }
                // right children does not exist -> left children must > root
                else if(rightChildren(k)>=last){
                        return leftChildren(k);
                }
                else {
                        // return the larger children's index
                        return heap[leftChildren(k)] > heap[rightChildren(k)] ? leftChildren(k):rightChildren(k);
                }

        }
}
