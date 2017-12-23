package aoj;

import java.util.*;

public class a2224
{
    private static class Point
    {
        int x, y;
        private Point(int x, int y)
        {
            this.x = x;
            this.y = y;
        }

        private double distanceTo(Point that)
        {
            return Math.sqrt(Math.pow(this.x-that.x, 2)+Math.pow(this.y-that.y, 2));
        }
    }
    private static class Edge
    {
        int v, w;
        double weight;
        private Edge(int v, int w, double weight)
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
        double val;
        private Node(int x, double val)
        {
            this.x = x;
            this.val = val;
        }
        public int compareTo(Node that)
        {
            return Double.compare(that.val, this.val);
        }
    }
    private static class Graph
    {
        ArrayList<ArrayList<Edge>> adj;
        boolean[] marked;
        double[] disTo;
        int v;
        double totalWeight;
        double mst;
        private Graph(int v)
        {
            this.v = v;
            marked = new boolean[v+1];
            disTo = new double[v+1];
            adj = new ArrayList<>(v+1);
            Arrays.fill(disTo, -1);
            for (int i = 0; i <= v; i++)
                adj.add(new ArrayList<>());
        }
        private void addEdge(Edge e)
        {
            totalWeight += e.weight;
            int v = e.v, w = e.another(v);
            adj.get(v).add(e);
            adj.get(w).add(e);
        }
        public void solve(int s)
        {
            PriorityQueue<Node> pq = new PriorityQueue<>(v);
            disTo[s] = 0.0;
            pq.add(new Node(s, 0.0));
            ArrayList<Integer> visited = new ArrayList<>();
            while (!pq.isEmpty())
            {
                Node p = pq.poll();
                int u = p.x;
                if (marked[u]) continue;
                marked[u] = true;
                visited.add(u);
                for (int i = 0; i < adj.get(u).size(); i++)
                {
                    Edge e = adj.get(u).get(i);
                    int v = e.another(u);
                    if (marked[v]) continue;
                    double w = e.weight;
                    if (w > disTo[v])
                    {
                        disTo[v] = w;
                        pq.add(new Node(v, w));
                    }
                }
            }
            for (int i: visited)
            {
                mst += disTo[i];
            }

        }
    }

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        HashMap<Integer, Point> map = new HashMap<>(n);
        Graph g = new Graph(n);
        for (int i = 1; i <= n; i++)
            map.put(i, new Point(in.nextInt(), in.nextInt()));

        for (int i = 0; i < m; i++)
        {
            int v = in.nextInt(), w = in.nextInt();
            double weight = map.get(v).distanceTo(map.get(w));
            g.addEdge(new Edge(v, w, weight));
        }
        for (int i = 1; i <= n; i++)
        {
            if (!g.marked[i])
                g.solve(i);
        }
        System.out.printf("%.3f", g.totalWeight - g.mst);
    }
}
