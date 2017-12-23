package poj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class p3268
{
    private static class Edge
    {
        int from, to, cost;
        private Edge(int from, int to, int cost)
        {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
        private int another(int v)
        {
            return from == v ? to : from;
        }
    }

    private static class Node implements Comparable<Node>
    {
        int x, val;
        private Node(int x, int val)
        {
            this.x = x;
            this.val = val;
        }

        public int compareTo(Node that)
        {
            return Integer.valueOf(this.val).compareTo(Integer.valueOf(that.val));
        }
    }
    private static class Solver
    {
        ArrayList<ArrayList<Edge>> adj;
        ArrayList<ArrayList<Edge>> reAdj;
        int[] disTo;
        int[] reDisTo;
        boolean[] marked;
        boolean[] reMarked;
        int s;
        private Solver(int v, int s)
        {
            adj = new ArrayList<ArrayList<Edge>>(v);
            reAdj = new ArrayList<ArrayList<Edge>>(v);
            disTo = new int[v];
            reDisTo = new int[v];
            marked = new boolean[v];
            reMarked = new boolean[v];
            this.s = s;
            for (int i = 0; i < v; i++)
            {
                adj.add(new ArrayList<Edge>());
                reAdj.add(new ArrayList<Edge>());
            }
        }

        private void addEdge(Edge e)
        {
            int from = e.from, to = e.to;
            adj.get(from).add(e);
            reAdj.get(to).add(e);
        }
        private void dijkstra(ArrayList<ArrayList<Edge>> adj, int[] disTo, boolean[] marked, int s)
        {
            Arrays.fill(disTo, 0xfffff);
            PriorityQueue<Node> pq = new PriorityQueue<Node>();
            disTo[s] = 0;
            pq.add(new Node(s, 0));
            while (!pq.isEmpty())
            {
                Node p = pq.poll();
                marked[p.x] = true;
                for (Edge e: adj.get(p.x))
                {
                    int v = e.another(p.x);
                    if (marked[v]) continue;
                    if (disTo[v] > disTo[p.x] + e.cost)
                    {
                        disTo[v] = disTo[p.x] + e.cost;
                        pq.add(new Node(v, disTo[v]));
                    }
                }
            }
        }

        private void run()
        {
            dijkstra(adj, disTo, marked, s);
            dijkstra(reAdj, reDisTo, reMarked, s);
            int maxDis = 0;
            for (int i = 0; i < disTo.length; i++)
            {
                if (disTo[i] != 0xfffff && reDisTo[i] != 0xfffff)
                {
                    maxDis = Math.max(disTo[i]+reDisTo[i], maxDis);
                }

            }
            System.out.println(maxDis);
        }
    }

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int s = in.nextInt();
        Solver solver = new Solver(n, --s);
        for (int i = 0; i < m; i++)
        {
            int from = in.nextInt(), to = in.nextInt(), cost = in.nextInt();
            solver.addEdge(new Edge(--from, --to, cost));
        }

        solver.run();
    }
}
