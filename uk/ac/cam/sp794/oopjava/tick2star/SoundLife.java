package uk.ac.cam.sp794.oopjava.tick2star;

import uk.ac.cam.acr31.life.World;
import uk.ac.cam.acr31.sound.AudioSequence;
import uk.ac.cam.acr31.sound.SineWaveSound;
import java.io.FileOutputStream;
import java.io.OutputStream;
public class SoundLife{
        public static void main(String[] args) throws java.io.IOException {
                String generationStr = String.valueOf(getPreferredGenerations());
                String pattern  = getPreferredPattern();
                String outputFilePath;
                if (args.length > 3 || args.length < 1) {
                        System.out.println("SoundLife [optional pattern] [optional no. of generation] [output file]");
                        return;
                }
                else if(args.length == 3){
                        pattern = args[0];
                        generationStr = args[1];
                }
                else if (args.length == 2){
                        if(args[0].contains(":"))
                                pattern = args[0];
                        else
                                generationStr = args[0];
                }

                outputFilePath = args[args.length-1];

                World world;
                try{
                        Pattern p = new Pattern(pattern);
                        int noGeneration = Integer.parseInt(generationStr);
                        //if(!outputFilePath.endsWith(".wav"))
                                //outputFilePath += ".wav";
                        OutputStream outputFile = new FileOutputStream(outputFilePath);

                        world = new ArrayWorld(p.getWidth(),p.getHeight());
                        p.initialise(world);
                        play(world,outputFile,noGeneration);
                }catch(PatternFormatException e){
                        System.out.println(e.getMessage());
                        return;
                }catch(NumberFormatException e){
                        System.out.println("Generation must be a positive integer.");
                        return;
                }
        }
        public static String getPreferredPattern(){
                return "Infinited life:Pan Song:100:100:45:45:00000010 00001011 00001010 00001000 00100000 10100000";
        }
        public static int getPreferredGenerations() {
                return 100;
        }

        public static double power(double base, int exp){
                double product = 1;
                while(exp!=0){
                        if((exp&1)==1){
                                product *= base;
                        }
                        base *= base;
                        exp >>=1;
                }
                return product;
        }
        public static void play(World world, OutputStream o, int noGeneration ) throws java.io.IOException {

                AudioSequence as = new AudioSequence(0.1);
                final double base = 110;
                final double ratio = 125/110.0;
                while((noGeneration--)!=0){
                        double volume = ((double)world.getPopulation())/(world.getHeight()*world.getWidth());
                        while(volume < 0.1)
                                volume *= 10.0;
                        double freq = base*power(ratio,(int)(volume*10));
                        as.addSound(new SineWaveSound(freq,volume));
                        as.advance();
                        world = world.nextGeneration(0);
                }
                as.write(o);
        }

}
