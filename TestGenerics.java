public class TestGenerics<T>{
        private T a;
        private T[] b;

        public TestGenerics(){
                // Wrong
                b = new T[10];
        }
}
