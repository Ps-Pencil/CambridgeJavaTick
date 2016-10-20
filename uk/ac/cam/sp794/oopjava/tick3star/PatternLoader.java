package uk.ac.cam.sp794.oopjava.tick3star;

import java.io.Reader;
import java.io.IOException;
import java.util.List;
import java.io.BufferedReader;
import java.util.LinkedList;
import java.net.URL;
import java.net.URLConnection;
import java.io.InputStreamReader;
import java.io.FileReader;
public class PatternLoader {

   public static List<Pattern> load(Reader r) throws IOException {
           //TODO: Complete the implementation of this method.
           BufferedReader buff = new BufferedReader(r);
           List<Pattern> resultList = new LinkedList<Pattern>();
           String line;
           while((line = buff.readLine())!=null){
                   try{
                           Pattern p = new Pattern(line);
                           resultList.add(p);
                   } catch (PatternFormatException e){
                           //ignore it
                   }
           }
           return resultList;
   }
   public static List<Pattern> loadFromURL(String url) throws IOException {
           URL destination = new URL(url);
           URLConnection conn = destination.openConnection();
           return load(new InputStreamReader(conn.getInputStream()));
   }
   public static List<Pattern> loadFromDisk(String filename) throws IOException {
           return load(new FileReader(filename));
   }
}
