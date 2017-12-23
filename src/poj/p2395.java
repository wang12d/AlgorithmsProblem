package poj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class p2395
{
    private static class Edge
    {
        int v, w, weight;
        public Edge(int v, int w, int weight)
        {
            this.v = v;
            this.w = w;
            this.weight = weight;
        }
        public int another(int v)
        {
            if (v == this.v)    return w;
            else                return this.v;
        }
    }

    private static class Node implements Comparable<Node>
    {
        int x;
        long val;
        public Node(int x, long val)
        {
            this.x = x;
            this.val = val;
        }
        public int compareTo(Node that)
        {
            return Long.valueOf(this.val).compareTo(Long.valueOf(that.val));
        }
    }
    private static class Graph
    {
        ArrayList<ArrayList<Edge>> adj;
        long[] disTo;
        boolean[] marked;
        int v;
        static final long INF = 2000000000;
        public Graph(int v)
        {
            this.v = v;
            adj = new ArrayList<ArrayList<Edge>>(v+1);
            disTo = new long[v+1];
            marked = new boolean[v+1];
            Arrays.fill(disTo, INF);
            for (int i = 0; i <= v; i++)
                adj.add(new ArrayList<Edge>());
        }
        public void addEdge(Edge e)
        {
            int v = e.v, w = e.another(v);
            adj.get(v).add(e);
            adj.get(w).add(e);
        }
        private void prim(int s)
        {
            disTo[s] = 0;
            PriorityQueue<Node> pq = new PriorityQueue<Node>(v+1);
            pq.add(new Node(s, 0));
            while (!pq.isEmpty())
            {
                Node p = pq.poll();
                int u = p.x;
                marked[u] = true;
                for (int i = 0; i < adj.get(u).size(); i++) {
                    Edge e = adj.get(u).get(i);
                    int v = e.another(u);
                    if (marked[v])  continue;
                    if (e.weight < disTo[v])
                    {
                        disTo[v] = e.weight;
                        pq.add(new Node(v, disTo[v]));
                    }
                }
            }
            long ans = 0;
            for (int i = 1; i <= v; i++)
                ans = Math.max(ans, disTo[i]);

            System.out.println(ans);
        }
    }
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        Graph g = new Graph(n);
        for (int i = 0; i < m; i++)
            g.addEdge(new Edge(in.nextInt(), in.nextInt(), in.nextInt()));

        g.prim(1);
    }
}
