import java.util.*;
import java.io.*;

public class EdmondsKarp {
    private static int MAX_V = 50;
    private static int INF = 1_000_000_000;

    private static int[][] res = new int[MAX_V][];
    private static int mf, f, s, t;
    private static ArrayList<Integer> p = new ArrayList<>();

    private static void augment(int v, int minEdge){
        if (v == s) {
            f = minEdge;
            return;
        }
        else if(p.get(v) != -1){
            augment(p.get(v), Math.min(minEdge, res[p.get(v)][v]));
            res[p.get(v)][v] -= f;
            res[v][p.get(v)] += f;
        }
    }
    public static void main(String[] args)  throws IOException {
        int V, k, vertex, weight;

        Scanner sc = new Scanner(new File("in.txt"));

        V = sc.nextInt();
        s = sc.nextInt();
        t = sc.nextInt();

        for(int i=0; i<V; i++){
            res[i] = new int[MAX_V];
            k = sc.nextInt();
            for(int j=0; j<k;j++){
                vertex = sc.nextInt();
                weight = sc.nextInt();
                res[i][vertex] = weight;       
            }
        }

        mf = 0;
        
        while(true){
            f = 0;

            Queue<Integer> q = new LinkedList<>();
            ArrayList<Integer> dist = new ArrayList<>();

            dist.addAll(Collections.nCopies(V, INF));
            q.offer(s);
            dist.set(s, 0);
            p.clear();
            p.addAll(Collections.nCopies(V, -1));

            while(!q.isEmpty()){
                int u = q.poll();

                if(u == t) break;

                for(int v=0; v<MAX_V; v++){
                    if(res[u][v] > 0 && dist.get(v) == INF){
                        dist.set(v, dist.get(u) + 1);
                        q.offer(v);
                        p.set(v,u);
                    }
                }                
            }
            augment(t, INF);

            if(f==0) break;

            mf += f;
        }

        System.out.printf("Max flow: %d\n", mf);
    }
}