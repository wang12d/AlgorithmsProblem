package aoj;

import java.util.*;

public class a0189
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
            return v == this.v ? w : this.v;
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
    private static class Graph
    {
        int v;
        boolean[] marked;
        int[] disTo;
        ArrayList<ArrayList<Edge>> adj;
        public Graph(int v)
        {
            this.v = v;
            marked = new boolean[v];
            disTo = new int[v];
            adj = new ArrayList<>(v);
            for (int i = 0; i < v; i++)
            {
                adj.add(new ArrayList<>());
            }
        }
        public void addEdge(Edge e)
        {
            int v = e.v, w = e.another(v);
            adj.get(v).add(e);
            adj.get(w).add(e);
        }
        public void solve()
        {
            Node ans = new Node(0, Integer.MAX_VALUE);
            for (int i = 0; i < v; i++)
            {
                Arrays.fill(disTo, 200);
                Arrays.fill(marked, false);
                disTo[i] = 0;
                PriorityQueue<Node> pq = new PriorityQueue<>();
                pq.add(new Node(i, 0));
                while (!pq.isEmpty())
                {
                    Node p = pq.poll();
                    marked[p.x] = true;
                    for (int j = 0; j < adj.get(p.x).size(); j++)
                    {
                        Edge e = adj.get(p.x).get(j);
                        int u = e.another(p.x);
                        if (marked[u]) continue;
                        if (disTo[u] > disTo[p.x] + e.weight)
                        {
                            disTo[u] = disTo[p.x] + e.weight;
                            pq.add(new Node(u, disTo[u]));
                        }
                    }
                }
                int total = 0;
                for (int v: disTo)
                    total += v;

                if (ans.val > total)
                {
                    ans.x = i;
                    ans.val = total;
                }
            }
            System.out.println(ans.x + " " + ans.val);
        }
    }
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        while (in.hasNext())
        {
            Queue<Edge> q = new LinkedList<>();
            int n = in.nextInt();
            if (n == 0) break;
            int m = 0;
            for (int i = 0; i < n; i++)
            {
                int v = in.nextInt(), w = in.nextInt(), wg = in.nextInt();
                m = Math.max(v > w ? v : w, m);
                q.add(new Edge(v, w, wg));
            }
            Graph g = new Graph(m+1);
            for (Edge edge: q)
                g.addEdge(edge);

            g.solve();
        }
    }
}
