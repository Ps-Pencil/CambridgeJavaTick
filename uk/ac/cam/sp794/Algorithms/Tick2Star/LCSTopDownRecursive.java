package uk.ac.cam.sp794.Algorithms.Tick2Star;
import uk.ac.cam.rkh23.Algorithms.Tick2.LCSFinder;
import java.util.Arrays;
public class LCSTopDownRecursive extends LCSFinder{
        public LCSTopDownRecursive(String s1,String s2){
                super(s1,s2);
        }
        public int getLCSLength(){
                if(mString1.isEmpty() || mString2.isEmpty())
                        return 0;
                mTable = new int[mString1.length()][mString2.length()];
                // initialise with -1
                for(int[] row : mTable)
                        Arrays.fill(row,-1);
                return getLCSLength(mString1.length()-1,mString2.length()-1);
                
        }
        private int getLCSLength(int i, int j){
                // empty string
                if(i<0 || j<0)
                        return 0;
                // already computed
                if(mTable[i][j] != -1)
                        return mTable[i][j];

                // Similar to the way to generate
                // LCS STring
                if(mString1.charAt(i) == mString2.charAt(j)){
                        return mTable[i][j] = 1+getLCSLength(i-1,j-1);
                }
                else
                        return mTable[i][j] = Math.max(getLCSLength(i-1,j),getLCSLength(i,j-1));
        }
        public String getLCSString(){
                // check empty
                if(mString1.isEmpty() || mString2.isEmpty())
                        return "";

                // check if already computed
                if(mTable == null)
                        getLCSLength();
                int LCSLength = mTable[mString1.length()-1][mString2.length()-1];

                // compute LCS
                String lcs = "";
                int i = mString1.length()-1, j = mString2.length()-1;
                while(lcs.length() != LCSLength){
                        // if the current character is in LCS
                        if(mString1.charAt(i)==mString2.charAt(j)){
                                lcs = mString1.charAt(i)+lcs;
                                i--;
                                j--;
                        }
                        // if not
                        else {
                                if(j!=0 && mTable[i][j]==mTable[i][j-1])
                                        j--;
                                else
                                        i--;
                        }

                }
                return lcs;
        }

}
