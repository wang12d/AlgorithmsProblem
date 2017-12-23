package poj;

import java.util.*;

public class p1258EdtionOne {
    private static class Edge implements Comparable<Edge> {
        private final int v, w, weight;
        private Edge(int v, int w, int weight) {
            this.v = v;
            this.w = w;
            this.weight = weight;
        }
        public int weight() {
            return weight;
        }
        public int either() {
            return v;
        }
        public int another(int v) {
            if      (this.v == v)   return w;
            else if (w == v)        return this.v;
            else                    throw new IllegalArgumentException();
        }
        public int compareTo(Edge that) {
            return Integer.compare(this.weight, that.weight);
        }
    }
    private static class Graph {
        private ArrayList<ArrayList<Edge>> adj;
        private final int v;
        public Graph(int v) {
            this.v = v;
            adj = new ArrayList<>(v+1);
            for (int i = 0; i <= v; i++) {
                adj.add(new ArrayList<>());
            }
        }
        public void addEdge(Edge edge) {
            int v = edge.either(), w = edge.another(v);
            adj.get(v).add(edge);
            adj.get(w).add(edge);
        }
        public Iterable<Edge> adj(int v) {
            return adj.get(v);
        }
        public int V() {
            return v;
        }
    }
    private static class PrimMST {
        private boolean[] marked;
        private PriorityQueue<Edge> pq;
        private int totalWeight;
        public PrimMST(Graph G, int s) {
            marked = new boolean[G.V()+1];
            pq = new PriorityQueue<>(G.V()+1);
            visit(G, s);
            while (!pq.isEmpty()) {
                Edge e = pq.poll();
                int v = e.either(), w = e.another(v);
                if (marked[v] && marked[w]) continue;
                totalWeight += e.weight();
                if (!marked[v]) visit(G, v);
                if (!marked[w]) visit(G, w);
            }
        }
        private void visit(Graph G, int v) {
            marked[v] = true;
            for (Edge e: G.adj(v)) {
                if (!marked[e.another(v)]) pq.add(e);
            }
        }
        public void solve() {
            System.out.print(totalWeight);
        }
    }
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Graph graph = new Graph(n);
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
            {
                if (i < j) graph.addEdge(new Edge(i, j, in.nextInt()));
                else        in.nextInt();
            }
        PrimMST mst = new PrimMST(graph, 0);
        mst.solve();
    }
}
