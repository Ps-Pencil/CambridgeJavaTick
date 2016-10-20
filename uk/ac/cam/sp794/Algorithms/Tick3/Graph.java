package uk.ac.cam.sp794.Algorithms.Tick3;

import uk.ac.cam.rkh23.Algorithms.Tick3.GraphBase;
import uk.ac.cam.rkh23.Algorithms.Tick3.TargetUnreachable;
import uk.ac.cam.rkh23.Algorithms.Tick3.MaxFlowNetwork;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;
import java.io.IOException;
import java.net.URL;

public class Graph extends GraphBase{
    public Graph(URL url) throws IOException{
        super(url);
    }
    public Graph(String file) throws IOException{
        super(file);
    }
    public Graph(int adj[][]) throws IOException{
        super(adj);
    }

    public List<Integer> getFewestEdgesPath(int src, int target) throws TargetUnreachable{
        Queue<Integer> BFSQueue = new LinkedList<Integer>();
        int predecessor[] = new int[mN];
        boolean[] visited = new boolean[mN];

        // initially all nodes unvisited
        for(int i=0;i<mN;i++)
            visited[i]=false;

        // -1 to denote no predecessor of src
        predecessor[src]=-1;
        BFSQueue.add(src);
        visited[src] = true;

        while(!BFSQueue.isEmpty()){
            int cur = BFSQueue.remove();
            for(int i=0;i<mN;i++){
                if(!visited[i] && mAdj[cur][i]>0){
                    predecessor[i] = cur;
                    if(i == target){
                        List<Integer> ret = new LinkedList<Integer>();
                        // construct the path from back
                        while(i!=-1){
                            ret.add(0,i);
                            i = predecessor[i];
                        }
                        return ret;
                    }
                    BFSQueue.add(i);
                    visited[i] = true;
                }
            }
        }
        // target unreachable from s
        return new LinkedList<Integer>();
    }
    public MaxFlowNetwork getMaxFlow(int s, int t){
        // make a clone for the capacity network
        int[][] capacity = mAdj.clone();
        for(int i=0;i<mAdj.length;i++)
            capacity[i]=mAdj[i].clone();

        int flow = 0;
        try{
            List<Integer> l = getFewestEdgesPath(s,t);
            while(!l.isEmpty()){
                // find the bottle neck in the path
                int minEdge = mAdj[l.get(0)][l.get(1)];
                for(int i=1;i<l.size()-1;i++){
                    minEdge = Math.min(minEdge, mAdj[l.get(i)][l.get(i+1)]);
                }
                flow += minEdge;
                // tweak the residual graph
                for(int i=0;i<l.size()-1;i++){
                    mAdj[l.get(i)][l.get(i+1)] -= minEdge;
                    mAdj[l.get(i+1)][l.get(i)] += minEdge;
                }
                // find augmenting path again
                l = getFewestEdgesPath(s,t);
            }
            // compute flow network from capacity and residual
            int[][] flowNetwork = new int[mAdj.length][mAdj[0].length];
            for(int i=0;i<flowNetwork.length;i++)
                for(int j=0;j<flowNetwork[i].length;j++){
                    flowNetwork[i][j] = capacity[i][j]-mAdj[i][j];
                }
            // restore local variable
            mAdj = capacity.clone();
            for (int i = 0; i < mAdj.length; i++) {
                mAdj[i] = capacity[i].clone();
            } 
            return new MaxFlowNetwork(flow,new Graph(flowNetwork));
        }catch(IOException e){}
        catch(TargetUnreachable e){}
        // If exception happens, return something
        return new MaxFlowNetwork(-1,this);
    }

}
