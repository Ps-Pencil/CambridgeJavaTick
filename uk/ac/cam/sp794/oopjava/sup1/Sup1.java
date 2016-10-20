package uk.ac.cam.sp794.oopjava.sup1;
public class Sup1{
        public static void main(String[] args){
                int x;
                float y;
                String z;
                Object o;
        }
        // Question 4
        public static float[][] makeIdentityMatrix(int n){
                float[][] matrix = new float[n][n];
                for(int i=0;i<n;i++){
                        matrix[i][i]=1;
                }
                return matrix;
        }
        public static void transpose(float[][] matrix){
                if(matrix==null) ;//raise exception
                for(int i=0;i<matrix.length;i++)
                        for(int j=i+1;j<matrix[i].length;j++){
                                if(matrix[i]==null||matrix[i].length!=matrix.length) ;//raise exception
                               float temp = matrix[i][j];
                               matrix[i][j] = matrix[j][i];
                               matrix[j][i] = temp;
                        }
        }
        
}
//this class doesn't compile
class Vector2D{
        private int x;
        private int y;
        public int getX(){return x;}
        public int getY(){return y;}
        public void setX(int n){x=n;}
        public void setY(int n){y=n;}

        public void add(Vector2D v){

                x+=v.getX();
                y+=v.getY();
        }
        public Vector2D add(Vector2D v){
                Vector2D n = new Vector2D();
                n.setX(v.getX()+x);
                n.setY(v.getY()+y);
                return n;
        }
        //chaining a.add(b,c).multiply(d,e)....
        public Vector2D add(Vector2D v1, Vector2D v2){
                this.x = v1.getX()+v2.getX();
                this.y = v1.getY()+v2.getY();
                return this;
        }
        public static Vector2D add(Vector2D v1, Vector2D){
                //return v1 +v2
                return v1;
        }
}

