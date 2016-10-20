package TestInheritance;

public class Child implements Interface{
        public static void main(String[] args){
                Interface a = new Child();
                a.getSomething();
        }
        public int getSomething(){return 0;}
}
