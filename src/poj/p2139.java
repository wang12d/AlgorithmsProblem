package poj;

import java.util.Arrays;
import java.util.Scanner;

public class p2139
{
    private static void floydWarshall(int[][] graph)
    {
        for (int k = 0; k < graph.length; k++)
        {
            for (int i = 0; i < graph.length; i++)
            {
                for (int j = 0; j < graph.length; j++)
                {
                    graph[i][j] = Math.min(graph[i][j], graph[i][k]+graph[k][j]);
                }
            }
        }
    }
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int m = in.nextInt();
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++)
        {
            Arrays.fill(graph[i], 0xff);
            graph[i][i] = 0;
        }
        for (int i = 0; i < m; i++)
        {
            int k = in.nextInt();
            int[] movies = new int[k];
            for (int j = 0; j < k; j++)
            {
                movies[j] = in.nextInt();
                movies[j]--;
            }
            for (int j = 0; j < k; j++)
            {
                for (int v = j+1; v < k; v++)
                {
                    graph[movies[j]][movies[v]] = 1;
                    graph[movies[v]][movies[j]] = 1;
                }
            }
        }
        floydWarshall(graph);

        int degree = 0xfff;
        for (int i = 0; i < n; i++)
        {
            int sum = 0;
            for (int j = 0; j < n; j++)
            {
                sum += graph[i][j];
            }
            degree = Math.min(degree, sum);
        }

        System.out.println(100 * degree / (n - 1));
    }

}
