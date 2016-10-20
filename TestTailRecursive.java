class TestTailRecursive{
        public static void main(String args[]){
                int mode = Integer.parseInt(args[0]);
                long number = Integer.parseInt(args[1]);
                if(mode==0)
                        add_recursive(number);
                else
                        add_iterative(number,0);

        }
        private static int add_recursive(long n){
                if(n==0){
                        return 0;
                }
                return 1 + add_recursive(n-1);
        }
        //stack overflow error - should be no optimisation
        private static long add_iterative(long n, long acc){
                if(n==0) 
                        return acc;
                return add_iterative(n-1,acc+1);
        }
}
