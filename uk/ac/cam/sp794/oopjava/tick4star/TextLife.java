package uk.ac.cam.sp794.oopjava.tick4star;

import java.util.List;
import uk.ac.cam.acr31.life.World;
import java.io.IOException;
import java.io.OutputStreamWriter;
public class TextLife {
        public static void main(String[] args) {
                CommandLineOptions c;
                try{
                        c = new CommandLineOptions(args);
                        List<Pattern> list;
                        if (c.getSource().startsWith("http://")) {
                                list = PatternLoader.loadFromURL(c.getSource());
                        } else {
                                list = PatternLoader.loadFromDisk(c.getSource());
                        }
                        if (c.getIndex() == null) {
                                int i = 0;
                                for (Pattern p : list)
                                        System.out.println((i++)+"\t"+p.getName()+"\t"+p.getAuthor());
                        } else {
                                Pattern p = list.get(c.getIndex());
                                World w = null;
                                if (c.getWorldType().equals(CommandLineOptions.WORLD_TYPE_AGING)) {
                                        w = new AgingWorld(p.getWidth(), p.getHeight());
                                } else if (c.getWorldType().equals(CommandLineOptions.WORLD_TYPE_ARRAY)) {
                                        w = new ArrayWorld(p.getWidth(), p.getHeight());
                                } else {
                                        w = new PackedWorld();
                                }
                                p.initialise(w);
                                int userResponse = 0;
                                while (userResponse != 'q') {
                                        w.print(new OutputStreamWriter(System.out));
                                        try {
                                                userResponse = System.in.read();
                                        } catch (IOException e) {
                                                //just exit the program
                                                return;
                                        }
                                        w = w.nextGeneration(0);
                                }
                        }

                } catch (CommandLineException e){
                        System.out.println(e.getMessage());
                        return;
                } catch (IOException e){
                        System.out.println("Cannot find source");
                        return;
                } catch (PatternFormatException e){
                        System.out.println(e.getMessage());
                        return;
                } catch (IndexOutOfBoundsException e){
                        System.out.println("Error: Index out of bounds");
                        return;
                }

        }
}
