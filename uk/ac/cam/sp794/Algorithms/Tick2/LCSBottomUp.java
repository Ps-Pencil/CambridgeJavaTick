package uk.ac.cam.sp794.Algorithms.Tick2;

import uk.ac.cam.rkh23.Algorithms.Tick2.LCSFinder;

public class LCSBottomUp extends LCSFinder{
        public LCSBottomUp(String s1,String s2){
                super(s1,s2);
        }
        // for own debug use
        public void debug(){
                for(int i=0;i<mTable.length;i++){
                        for(int j=0;j<mTable[0].length;j++)
                                System.out.print(mTable[i][j]+" ");
                        System.out.println();
                }
        }


        public int getLCSLength(){
                // check if string is empty
                if(mString1.isEmpty()|| mString2.isEmpty())
                        return 0;

                // initialise array
                mTable = new int[mString1.length()][mString2.length()];
                mTable[0][0] = mString1.charAt(0)==mString2.charAt(0)?1:0;
                for(int i=1;i<mTable.length;i++){
                        mTable[i][0] = mTable[i-1][0]==1||mString1.charAt(i)==mString2.charAt(0)?1:0;
                }
                for(int i=1;i<mTable[0].length;i++){
                        mTable[0][i] = mTable[0][i-1]==1||mString1.charAt(0)==mString2.charAt(i)?1:0;
                }

                // DP step
                for(int i=1;i<mTable.length;i++){
                        for(int j=1;j<mTable[0].length;j++){
                                mTable[i][j] = mString1.charAt(i) == mString2.charAt(j) ? 1+mTable[i-1][j-1] : Math.max(mTable[i][j-1],mTable[i-1][j]);
                        }
                }

                return mTable[mString1.length()-1][mString2.length()-1];

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
