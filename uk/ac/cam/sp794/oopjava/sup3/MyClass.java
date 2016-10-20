public class MyClass implements Cloneable{
        private String mName;
        private final int[] mData = new int[100];

        //public MyClass(MyClass toCopy){
                //this.mName = toCopy.mName;
                //if(toCopy.mData != null){
                        //this.mData = new int[toCopy.mData.length];
                        //for(int i = 0; i < mData.length; i++)
                                //this.mData[i] = toCopy.mData[i];
                //}
                
        //}

        // copy constructor may not copy child states 
        // new ParentType(ChildType)
       
        // could be useful if there is anything in ChindType that we do 
        // not want in ParentTyp
       
        // Requiring Java 8
        @Override
        public MyClass clone() throws CloneNotSupportedException{
                MyClass cloned = (MyClass) super.clone();
                for(int i = 0; i < mData.length; i++)
                        cloned.mData[i] = this.mData[i];
                return cloned;
        }
        public static void main(String[] args) throws CloneNotSupportedException{
                MyClass a = new MyClass();
                a.mName = "abc";
                a.mData[0] = 1;
                MyClass b = a.clone();
                System.out.println(b.mName);
                System.out.println(b.mData[0]);
        }
}
