// Question 18 extends from question 12
class OOPLL{
        protected static class OOPLLE{
                int value;
                OOPLLE nxt;
                OOPLLE(int v, OOPLLE n) {value = v; nxt = n;}
        }
        protected OOPLLE head = null;
        //BAD naming practice
        void pushHead(final int i) {head = new OOPLLE(i,head);}
        int popHead() throws Exception{
                if(head == null)
                        throw new Exception("List too short for this operation");
                int x = head.value;
                head = head.nxt;
                return x;
        }
        int getLength(){
                int L = 0;
                for (OOPLLE t = head; t!= null; t=t.nxt) ++L;
                return L;
        }
        int getNth(int n) throws Exception{
                OOPLLE t = head;
                for(;n-- >0 && t!=null;t=t.nxt );
                if(t==null)
                        throw new Exception("List too short for this operation");
                return t.value;
        }
        OOPLL reverse(){
                OOPLL reversed = new OOPLL();
                OOPLLE cur = head;
                while(cur!=null){
                        reversed.pushHead(cur.value);
                        cur = cur.nxt;
                }
                return reversed;
        }
        void print(){
                OOPLLE cur = head;
                while(cur!=null){
                        System.out.println(cur.value);
                        cur=cur.nxt;
                }
                return;
        }
}

class OOPSortedLinkedList extends OOPLL{
        @Override
        void pushHead(final int i){
                OOPLLE cur = head;
                OOPLLE prev = head;
                if(cur == null || cur.value >= i){
                        head = new OOPLLE(i,head);
                } else {
                        while(cur!=null && cur.value < i){
                                prev = cur;
                                cur = cur.nxt;
                        }
                        //Alternative
                        // OOPLLE tmp = head;
                        // for(;tmp.next!=null && tmp.nxt.value < i;tmp=tmp.nxt);
                        // tmp.nxt = new OOPLLE(i,tmp.nxt);
                        prev.nxt = new OOPLLE(i,cur);
                }
        }
}
interface Queue<T>{
        void enqueue(T... item);
        T    dequeue();
}
class OOPListQueue<T>{
        // Assume OOPLL is parameterised;
        OOPLL<T> head = new OOPLL<T>();
        OOPLL<T> tail = new OOPLL<T>();
        void enqueue(T... items){
                for(T item : items){
                        tail.pushHead(item);
                }
        } 
        T dequeue(){
               if(head.getLength() == 0 && tail.getLength() == 0)
                       throw new RuntimeException();
               else if(head.getLength() == 0){
                       head = tail.reverse();
                       tail = new OOPLL<T>();
               }
               return head.popHead();
        }
        
}
class sup2{
        public static void main(String args[]) throws Exception {
                OOPSortedLinkedList ll = new OOPSortedLinkedList();
                ll.pushHead(3);
                ll.pushHead(2);
                ll.popHead();
                ll.pushHead(4);
                ll.pushHead(1);
                
                ll.reverse().print();
        }
}
