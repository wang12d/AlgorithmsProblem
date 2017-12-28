package poj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class p3662
{
    private static class Edge
    {
        private int v, cost;
        private Edge(int v, int cost)
        {
            this.v = v;
            this.cost = cost;
        }
    }

    private static class Pair implements Comparable<Pair>
    {
        private int x, val;
        private Pair(int x, int val)
        {
            this.x = x;
            this.val = val;
        }
        public int compareTo(Pair that)
        {
            return this.val > that.val ? 1 : -1;
        }
    }
    private static int MAX_N = 1000 + 16;
    private static ArrayList<ArrayList<Edge>> edges = new ArrayList<ArrayList<Edge>>(MAX_N);
    private static boolean flag = false;    // 是否有解
    private static int n;
    private static boolean C(int mid, int k)
    {
        PriorityQueue<Pair> q = new PriorityQueue<Pair>();
        q.add(new Pair(0, 0));
        boolean[] marked = new boolean[n+1];
        int[] disTo = new int[n+1];
        Arrays.fill(disTo, Integer.MAX_VALUE);

        disTo[0] = 0;
        while (!q.isEmpty())
        {
            Pair p = q.poll();
            marked[p.x] = true;
            if (p.x == n) {flag = true; break;}
            for (Edge e: edges.get(p.x))
            {
                if (!marked[e.v] && disTo[e.v] > (e.cost > mid ? 1 : 0) + disTo[p.x])
                {
                    disTo[e.v] = disTo[p.x] + (e.cost > mid ? 1 : 0);
                    q.add(new Pair(e.v, disTo[e.v]));
                }
            }
        }

        return disTo[n] <= k;
    }
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        n = in.nextInt(); int p = in.nextInt(), k = in.nextInt();
        for (int i = 0; i < n; i++)
        {
            edges.add(new ArrayList<Edge>());
        }

        n--;
        for (int i = 0; i < p; i++)
        {
            int v = in.nextInt(), w = in.nextInt(), cost = in.nextInt();
            v--; w--;
            edges.get(v).add(new Edge(w, cost));
            edges.get(w).add(new Edge(v, cost));
        }

        int lb = 0, ub = 1000001;
        while (lb <= ub)
        {
            int mid = (lb + ub) / 2;
            if (C(mid, k))      ub = mid - 1;
            else                lb = mid + 1;
        }
        System.out.println(flag ? lb : -1);
    }
}
