package uk.ac.cam.sp794.oopjava.tick5;
public class AnonTest2Run {

        public static void main(String[] args) {
                AnonTest2 anonTest = new AnonTest2();

                AnonTest2.A instance1 = anonTest.getA();
                AnonTest2.A instance2 = anonTest.getSpecialA();

                instance1.print();
                instance2.print();

                anonTest.incrementCounter();

                instance1.print();
                instance2.print();
        } 
}
