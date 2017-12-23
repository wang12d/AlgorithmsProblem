package poj;

import java.util.Scanner;
import java.util.Arrays;

public class p1258EdtionTwo {
    private static final int MAX_V = 101;
    private static final int[] minCost = new int[MAX_V];
    private static final int[][] cost = new int[MAX_V][MAX_V];
    private static final boolean[] used = new boolean[MAX_V];
    private static final int INF = 100000000;

    private static long prim(int V) {
        Arrays.fill(minCost, 0, V+1, INF);
        Arrays.fill(used, 0, V+1, false);
        minCost[0] = 0;
        long res = 0;

        while (true) {
            int v = -1;
            for (int u = 0; u < V; u++) {
                if (!used[u] && (v == -1 || minCost[u] < minCost[v]))
                    v = u;
            }
                if (v == -1) break;
                used[v] = true;
                res += minCost[v];

                for (int u = 0; u < V; u++) {
                    if (!used[u]) minCost[u] = Math.min(minCost[u], cost[v][u]);
                }
            }

        return res;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int V = in.nextInt();
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                cost[i][j] = in.nextInt();
            }
        }
        System.out.println(prim(V));
    }
}
