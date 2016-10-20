import java.util.TreeMap;
import java.util.List;
import java.util.Collections;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Map;
import java.lang.Exception;

class StudentMark{
        private final Map<String,Number> marks_map = new TreeMap<String,Number>();
        public StudentMark(Number[] marks, String[] names){
                for(int i = 0 ; i < names.length; i++){
                        marks_map.put(names[i],marks[i]);
                }
        }
        public List<String> allStudents(){

                List<String> names = new ArrayList<String>(marks_map.keySet());
                Collections.sort(names);
                return names;
        }
        public List<String> topPPercent(float p){
                if(p<=0 ||p>=100) 
                        throw new Exception("...");

                int nStudents = marks_map.size();
                int numReq = (int)((float)nStudents - (float)nStudents * p / 100);
                List<Number> marks = new LinkedList<Number>(marks_map.values());
                Collections.sort(marks);
                for(int i = 0 ; i < numReq; i++)
                        marks.remove(0);
                List<String> ret = new LinkedList<String>();
                for(String name : marks_map.keySet())
                        if(marks.contains(marks_map.get(name))) ret.add(name);
                return ret;
               
        }
        public float median(){
                
        }

        public static void main(String[] args){
                String[] names = {"A","B","C"};
                Number[] marks = {2.1,1.2,3.2};


        }
}
