import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
public class Maths{
       private String n1,n2,result;
       private HashMap<Character,Integer> map;
       public Maths(String s1,String s2, String s3){
               n1 = s1;
               n2 = s2;
               result = s3;
               map = new HashMap<Character,Integer>();
       }
       public void preset(char c, int a){
               map.put(c,a);
       }
       private void verify(HashMap<Character, Integer> mapping){
                if(convert(mapping,n1)+convert(mapping,n2) == convert(mapping,result)){
                        for(Entry<Character,Integer> e : mapping.entrySet())
                                System.out.println(e.getKey()+" "+e.getValue());
                        System.out.println(n1+" "+convert(mapping,n1));
                        System.out.println(n2+" "+convert(mapping,n2));
                        System.out.println(result+" "+convert(mapping,result));
                }

       }
       private int convert(HashMap<Character, Integer> mapping, String s){
               int res = 0;
               for(int i=0;i<s.toCharArray().length;i++){
                       res*=10;
                       res+=mapping.get(s.toCharArray()[i]);
               }
               return res;
       }
       public void solve(){
               int used = 0;
               for(Entry<Character,Integer> e: map.entrySet())
                       used|=(1<<e.getValue());
               List<Character> l = new ArrayList<Character>();
               String s = n1+n2+result;
               for(int i=0;i<s.toCharArray().length;i++){
                       if(!l.contains(s.toCharArray()[i]) && !map.keySet().contains(s.toCharArray()[i]))
                               l.add(s.toCharArray()[i]);

               }
               bruteforce(map,used,l);
       }
       private void bruteforce(HashMap<Character,Integer> mapping, int used, List<Character> letters){
               if(letters.isEmpty()){
                       verify(mapping);
               } else{
                       char c = letters.get(0);
                       letters.remove(0);
                       for(int i=0;i<10;i++){
                               if((used&(1<<i))==0){
                                       mapping.put(c,i);
                                       bruteforce(mapping,used|(1<<i),letters);
                                       mapping.remove(c);
                               }
                       }
                       letters.add(c);
               }
       }
       public static void main(String[] args){
               Maths t = new Maths("ELGAR","ENIGMA","NIMROD");
               t.preset('O',0);
               t.solve();
       }
}
