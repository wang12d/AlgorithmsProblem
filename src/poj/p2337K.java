package poj;

import java.util.PriorityQueue;
import java.util.Scanner;

public class p2337K
{
    private static class Edge implements Comparable<Edge>
    {
        int v, w, weight;
        public Edge(int v, int w, int weight)
        {
            this.v = v;
            this.w = w;
            this.weight = weight;
        }
        public int either()
        {
            return v;
        }
        public int another(int v)
        {
            if (v == this.v)    return w;
            else                return this.v;
        }
        public int compareTo(Edge that)
        {
            return Integer.valueOf(that.weight).compareTo(Integer.valueOf(this.weight));
        }
    }
    static int[] id;
    static int[] sz;
    static PriorityQueue<Edge> pq;

    private static void init(int n)
    {
        pq = new PriorityQueue<>(n);
        id = new int[n+1];
        sz = new int[n+1];
        for (int i = 1; i <= n; i++)
        {
            id[i] = i;
            sz[i] = 1;
        }
    }

    private static int find(int q)
    {
        if (q == id[q]) return q;
        return          q = find(id[q]);
    }

    private static boolean connected(int p, int q)
    {
        return find(q) == find(p);
    }

    private static void union(int q, int p)
    {
        if (connected(p, q))
            return;

        int pRoot = find(p), qRoot = find(q);
        if (sz[pRoot] > sz[qRoot])  {id[qRoot] = pRoot; sz[pRoot] += sz[qRoot];}
        else                        {id[pRoot] = qRoot; sz[qRoot] += sz[pRoot];}
    }

    private static void solve(int n)
    {
        long total = 0;
        int count = 0;
        while (!pq.isEmpty() && count < n-1)
        {
            Edge e = pq.poll();
            int v = e.either(), w = e.another(v);
            if (connected(v, w)) continue;
            union(v, w);
            total += e.weight;
            count++;
        }
        System.out.println((count == n-1) ? total : -1);
    }

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        init(n);
        for (int i = 0; i < m; i++)
        {
            pq.add(new Edge(in.nextInt(), in.nextInt(), in.nextInt()));
        }
        solve(n);
        in.close();
    }
}
