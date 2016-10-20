package uk.ac.cam.sp794.Algorithms.Tick2Star;

import uk.ac.cam.rkh23.Algorithms.Tick2.LCSFinder;
import java.util.Arrays;
import java.util.ArrayList;
public class LCSTopDownNonRecursive extends LCSFinder{
        public LCSTopDownNonRecursive(String s1,String s2){
                super(s1,s2);
        }
        public int getLCSLength(){
                if(mString1.isEmpty() || mString2.isEmpty())
                        return 0;
                mTable = new int[mString1.length()][mString2.length()];
                // initialise with -1
                for(int[] row : mTable)
                        Arrays.fill(row,-1);
                // local stack
                ArrayList<Integer> callStack = new ArrayList<Integer>();

                // push parameters, right to left
                callStack.add(mString2.length()-1);
                callStack.add(mString1.length()-1);
                while(!callStack.isEmpty()){
                        // get the top two elements 
                        int i = callStack.get(callStack.size()-1);
                        int j = callStack.get(callStack.size()-2);

                        // already computed
                        if(mTable[i][j] != -1){
                                callStack.remove(callStack.size()-1);
                                callStack.remove(callStack.size()-1);
                                continue;
                        }

                        // Similar to the way to generate
                        // LCS STring
                        if(mString1.charAt(i) == mString2.charAt(j)){
                                if(i == 0 || j == 0){
                                        // if reached end compute
                                        // and pop
                                        mTable[i][j] = 1;
                                        callStack.remove(callStack.size()-1);
                                        callStack.remove(callStack.size()-1);
                                        continue;
                                }
                                else if(mTable[i-1][j-1] == -1){
                                        // compute subcases
                                        callStack.add(j-1);
                                        callStack.add(i-1);
                                        continue;
                                }
                                // pop
                                mTable[i][j] = 1+mTable[i-1][j-1];
                                callStack.remove(callStack.size()-1);
                                callStack.remove(callStack.size()-1);
                        }
                        else{
                                // different char at i and j
                                if(i==0 && j == 0){
                                        mTable[i][j] = 0;
                                callStack.remove(callStack.size()-1);
                                callStack.remove(callStack.size()-1);
                                continue;
                                }
                                int ans = 0;
                                if(i!=0 && mTable[i-1][j] == -1){
                                        callStack.add(j);
                                        callStack.add(i-1);
                                        continue;
                                } else if(i!=0)
                                        ans = Math.max(ans,mTable[i-1][j]);
                                if(j!=0 && mTable[i][j-1] == -1){
                                        callStack.add(j-1);
                                        callStack.add(i);
                                        continue;
                                } else if(j!=0)
                                        ans = Math.max(ans,mTable[i][j-1]);
                                mTable[i][j] = ans;
                                callStack.remove(callStack.size()-1);
                                callStack.remove(callStack.size()-1);
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
