package uk.ac.cam.sp794.assignment4;

public class FibonacciCache {
   //TODO: Test your program with values other than 20 as given here
   public static long[] fib = new long[20];

   public static void store() {
      //TODO: using a for loop, fill "fib" with the Fibonacci numbers 
      //      e.g. if length of "fib" is zero, store nothing; and
      //           if length is six, store 1,1,2,3,5,8 in "fib"
       fib[0] = 1;
       fib[1] = 1;
       for(int i=2;i<fib.length;i++){
           fib[i] = fib[i-1] + fib[i-2];
       }
   }

   public static void reset() {
      //TODO: using a for loop, set all the elements of fib to zero
       for(int i=0;i<fib.length;i++)
           fib[i] = 0;
   }
 
   public static long get(int i) throws Exception/*TODO: declare the checked exception*/ {
      //TODO: return the value of the element in fib found at index i
      //      e.g. "get(3)" should return the fourth element of fib
       if(i<0 || i > fib.length - 1)
           throw new Exception("Array index out of bounds.");

       return fib[i];
      //TODO: your code should throw an Exception with a suitable message
      // if the value requested is out of bounds of the array
   }

   public static void main(String[] args) {
      //TODO: catch exceptions as appropriate and print error messages
       try {
      store();
      if(args.length == 0){
          throw new Exception("Please enter a number.");
      }
      int i = Integer.decode(args[0]);
      System.out.println(get(i));
       } catch (Exception e){
           System.out.println(e.getMessage());
       }
   }
  
}

