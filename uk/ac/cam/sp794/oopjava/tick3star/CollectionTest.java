package uk.ac.cam.sp794.oopjava.tick3star;

import java.util.Collections;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
public class CollectionTest {
        public static List<AuthorCount> processWithList(List<Pattern> patterns) {
                List<AuthorCount> list = new LinkedList<AuthorCount>();
                for(Pattern p : patterns) {
                        //TODO: determine if a suitable AuthorCount objects exists in
                        //'list' if so, increment the count for this author; if not add
                        //a suitable object to the list
                        boolean exist = false;
                        for(AuthorCount author : list){
                                if(author.getAuthor().equals(p.getAuthor())){
                                        author.inc();
                                        exist = true;
                                        break;
                                }
                        }
                        if(!exist)
                                list.add(new AuthorCount(p.getAuthor()));
                }
                //TODO: sort "list" and return it to caller.
                Collections.sort(list);
                return list;
        }
        public static List<AuthorCount> processWithMap(List<Pattern> patterns) {
                Map<String,AuthorCount> map = new HashMap<String,AuthorCount>();
                for(Pattern p : patterns) {
                        //TODO: determine if 'map' contains author name as key
                        //if so, increment associated AuthorCount object; if not add one.
                        if(map.containsKey(p.getAuthor()))
                                map.get(p.getAuthor()).inc();
                        else
                                map.put(p.getAuthor(),new AuthorCount(p.getAuthor()));
                }
                //TODO: copy set of AuthorCount objects associated with 'map' into
                //an ArrayList.  Sort the list of AuthorCount objects and return it
                //to the caller.
                List<AuthorCount> arrayList = new ArrayList<AuthorCount>(map.values());
                Collections.sort(arrayList);
                return arrayList;
        }
        public static void main(String[] args) {
                //TODO: write code in here to test both above implementations on the
                //pattern file http://www.cl.cam.ac.uk/teaching/current/OOProg/life.txt
                //This only test the authorcount.compareTo
                AuthorCount a = new AuthorCount("abc");
                AuthorCount b = new AuthorCount("bca");
                System.out.println(a.compareTo(b));
                b.inc();
                System.out.println(a.compareTo(b));

        } }
