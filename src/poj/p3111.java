package poj;

import java.io.*;
import java.util.Arrays;

public class p3111
{
    private static final int MAX_N = 100000 + 16;
    private static Node[] y = new Node[MAX_N];
    private static int N, K;
    private static StringBuilder ans = new StringBuilder();

    private static class Node implements Comparable<Node>
    {
        private double val;
        private final int id;
        private final int w;
        private final int v;
        private Node(int v, int w, int id)
        {
            this.v = v;
            this.w = w;
            this.id = id;
        }

        public int compareTo(Node that)
        {
            return Double.compare(that.val, this.val);
        }
    }

    private static boolean C(double k)
    {
        for (int i = 0; i < N; i++)
        {
            y[i].val = y[i].v - y[i].w * k;
        }
        Arrays.sort(y, 0, N);
        double sum = 0;
        for (int i = 0; i < K; i++)
        {
            sum += y[i].val;
        }
        return sum >= 0;
    }

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(str[0]);
        K = Integer.parseInt(str[1]);
        for (int i = 0; i < N; i++)
        {
            str = br.readLine().split(" ");
            y[i] = new Node(Integer.parseInt(str[0]), Integer.parseInt(str[1]), i+1);
        }

        double lb = 0, ub = 1000000.0;
        for (int i = 0; i < 50; i++)
        {
            double mid = (lb + ub) / 2;
            if (C(mid)) lb = mid;
            else        ub = mid;
        }
        int[] result = new int[K];
        for (int i = 0; i < K; i++)
        {
            result[i] = y[i].id;
        }
        Arrays.sort(result);
        for (int i: result)
        {
            ans.append(String.format("%d ", i));
        }
        ans.append("\n");
        bw.write(ans.toString());
        bw.close();
    }
}
