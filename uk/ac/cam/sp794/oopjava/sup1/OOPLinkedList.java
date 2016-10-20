import java.lang.Exception;


public class OOPLinkedList{ 
        static class OOPLinkedListElement{
                public int data;
                public OOPLinkedListElement next;
        }
        private OOPLinkedListElement head;
        
        private int length;
        public void addNode(final int n){
                OOPLinkedListElement node = new OOPLinkedListElement();
                node.data = n;
                node.next = head;
                head = node;
                length++;
        }
        public int removeNode() throws Exception{
                if(head == null) throw new Exception();
                int n = head.data;
                head = head.next;
                length--;
                return n;
        }
        public int getHead()throws Exception{
                if(head == null) throw new Exception();
                return head.data;
        } 
        public int nth(int n) throws Exception{
                if(n>=length) throw new Exception();
                int cur = 0;
                OOPLinkedListElement curNode = head;
                while(cur!=n){
                       curNode = curNode.next;
                       cur++;
                }
                return curNode.data;
        }
        // not threadsafe.
        public int length(){
                return length;
        }
}

