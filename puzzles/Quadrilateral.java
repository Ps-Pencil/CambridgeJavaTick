public class Quadrilateral{
        public static void main(String[] args){
                int a,b,c,d;
                final int MAX = 21;
                double sum1 = 0, sum2 = 0;
                for(a = 1;a<MAX;a++){
                        for(b=1;b<MAX;b++){
                                for(c=1;c<MAX;c++){
                                        for(d=1;d<MAX;d++){
                                                
                                                double ab = Math.sqrt(a*a+b*b-2*a*b*Math.cos(Math.PI/3.0));
                                                double bc = Math.sqrt(c*c+b*b-2*c*b*Math.cos(2.0*Math.PI/3.0));
                                                double cd = Math.sqrt(c*c+d*d);
                                                double ad = Math.sqrt(a*a+d*d);
                                                if(isInt(ab) && isInt(bc) && isInt(cd) && isInt(ad))
                                                        if(0==sum1)
                                                                sum1 = ab+bc+cd+ad;
                                                        else{
                                                                sum2 = ab+bc+cd+ad;
                                                        }
                                                

                                        }
                                }
                        }
                }
                System.out.println((int)Math.abs(sum1-sum2));
        }
        public static boolean isInt(double a){
                return Math.abs(a-Math.floor(a))<1e-6 || Math.abs(a-Math.ceil(a))<1e-6;
        }
}
