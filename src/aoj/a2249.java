package aoj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class a2249
{
    private static class Edge
    {
        int v, w, val, cost;
        public Edge(int v, int w,  int val, int cost)
        {
            this.v = v;
            this.w = w;
            this.val = val;
            this.cost = cost;
        }
        public int another(int v)
        {
            return this.v == v ? w : this.v;
        }
    }
    private static class Node implements Comparable<Node>
    {
        int x, val;
        public Node(int x, int val)
        {
            this.x = x;
            this.val = val;
        }
        public int compareTo(Node that)
        {
            return Integer.compare(this.val, that.val);
        }
    }
    private static class Solve
    {
        ArrayList<ArrayList<Edge>> adj;
        int v;
        int[] disTo;
        int[] cost;
        boolean[] marked;

        public Solve(int v)
        {
            this.v = v;
            disTo = new int[v+1];
            cost = new int[v+1];
            marked = new boolean[v+1];
            adj = new ArrayList<>(v+1);
            for (int i = 0; i <= v; i++)
                adj.add(new ArrayList<>());
        }

        public void addEdge(Edge e)
        {
            int v = e.v, w = e.another(v);
            adj.get(v).add(e);
            adj.get(w).add(e);
        }

        public void run()
        {
            PriorityQueue<Node> pq = new PriorityQueue<>();
            Arrays.fill(disTo, 200000);
            Arrays.fill(cost, 200000);
            disTo[1] = 0;
            cost[1] = 0;
            disTo[0] = 0;
            cost[0] = 0;
            pq.add(new Node(1, 0));
            while (!pq.isEmpty())
            {
                Node p = pq.poll();
                marked[p.x] = true;
                for (int i = 0; i < adj.get(p.x).size(); i++)
                {
                    Edge e = adj.get(p.x).get(i);
                    int u = e.another(p.x);
                    if (marked[u]) continue;
                    if (disTo[u] >= disTo[p.x] + e.val)
                    {
                        boolean flag = false;
                        if (disTo[u] > disTo[p.x] + e.val)
                        {
                            disTo[u] = disTo[p.x] + e.val;
                            flag = true;
                        }
                        if (flag)                       cost[u] = e.cost;
                        else if (cost[u] > e.cost)      cost[u] = e.cost;
                        pq.add(new Node(u, disTo[u]));
                    }
                }
            }
            int total = 0;
            for (int i: cost)
                total += i;

            System.out.println(total);
        }
    }

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        while (true)
        {
            int n = in.nextInt();
            int m = in.nextInt();
            if (n == 0 && m == 0) break;
            Solve solve = new Solve(n);
            for (int i = 0; i < m; i++)
            {
                solve.addEdge(new Edge(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt()));
            }
            solve.run();
        }
    }
}
