package poj;

import java.util.Arrays;
import java.util.Scanner;

public class p3259
{
    private static class Edge
    {
        int from , to, cost;
        public Edge(int from ,int to, int cost)
        {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    private static String solve(Edge[] edges, int s, int n)
    {
        int[] disTo = new int[edges.length];
        Arrays.fill(disTo, 0x5fff);
        int count = 0;
        disTo[s] = 0;
        while (true)
        {
            boolean update = false;
            for (int i = 0; i < edges.length; i++)
            {
                Edge e = edges[i];
                if (disTo[e.to] > disTo[e.from] + e.cost)
                {
                    disTo[e.to] = disTo[e.from] + e.cost;
                    update = true;
                }
            }

            if (!update)
            {
                break;
            }

            if (count == n)
            {
                return "YES";
            }
            count++;
        }

        return "NO";
    }

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        int t = in.nextInt();
        for (int i = 0; i < t; i++)
        {
            int n = in.nextInt(), m = in.nextInt(), w = in.nextInt();
            Edge[] edges = new Edge[2*m + w];
            for (int j = 0; j < m; j++)
            {
                int from = in.nextInt(), to = in.nextInt(), cost = in.nextInt();
                edges[2*j] = new Edge(--from, --to ,cost);
                edges[2*j+1] = new Edge(to, from, cost);
            }

            for (int j = 0; j < w; j++)
            {
                int from = in.nextInt(), to = in.nextInt(), cost = in.nextInt();
                edges[2*m+j] = new Edge(--from, --to, -cost);
            }

            System.out.println(solve(edges, 0, n));
        }
    }
}
