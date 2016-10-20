package uk.ac.cam.sp794.oopjava.tick4star;

public class CommandLineOptions {
        public static String WORLD_TYPE_LONG = "--long";
        public static String WORLD_TYPE_AGING = "--aging";
        public static String WORLD_TYPE_ARRAY = "--array";
        private String worldType = null;
        private Integer index = null;
        private String source = null;
        public CommandLineOptions(String[] args) throws CommandLineException {
                //TODO: parse "args" to update the private fields "worldType",
                // "index" and "source" with the correct values, if any.
                if(args.length == 0)
                        throw new CommandLineException("Error: No arguments found");
                String patternIndex = null;
                int haveType = 0;
                if(args[0].startsWith("--")){
                        worldType = args[0];
                        haveType = 1;
                }else
                        worldType = "--array";

                if(args.length >= 1 + haveType)
                        source = args[0+haveType];
                if(args.length >= 2 + haveType)
                        patternIndex = args[1 + haveType];

                if(source == null){
                        throw new CommandLineException("Please specify a pattern source.");
                } 
                try{
                        if(patternIndex != null)
                                index = Integer.parseInt(patternIndex);

                } catch(NumberFormatException e){
                        throw new CommandLineException("Could not interpret patternIndex as an integer (given '"+patternIndex+"').");
                }
        }

public String getWorldType() {return worldType;}
public Integer getIndex() {return index;}
public String getSource() {return source;}
}
