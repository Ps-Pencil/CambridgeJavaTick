package uk.ac.cam.sp794.oopjava.tick1;

public class Repeat {
   public static void main(String[] args) {
      System.out.println(parseAndRep(args));
   }

   /*
    * Return the first string repeated by the number of times
    * specified by the integer in the second string, for example 
    *   
    *    parseAndRep(new String[]{"one","3"}) 
    *
    * should return "one one one". Adjacent copies of the repeated 
    * string should be separated by a single space.
    *
    * Return a suitable error message in a string when there are 
    * not enough arguments in 'args' or the second argument is 
    * not a valid positive integer. For example:
    *
    *  - parseAndRep(new String[]{"one"}) should return 
    *    "Error: insufficient arguments"
    *
    *  - parseAndRep(new String[]{"one","five"}) should return 
    *    "Error: second argument is not a positive integer"
    */
   public static String parseAndRep(String[] args) {
      //TODO
      if(args.length<2){
              return "Error: insufficient arguments";
      }
      try{
                int times = Integer.parseInt(args[1]);
                if(times <= 0)
                        throw new NumberFormatException();
                String result = "";
                for(int i=0;i<times;i++){
                        result += args[0] +' ';
                }
                if(result.length()>0)
                        result = result.substring(0,result.length()-1); 
                return result;
      } catch(NumberFormatException e){
              return "Error: second argument is not a positive integer";
      }
   }  
}
