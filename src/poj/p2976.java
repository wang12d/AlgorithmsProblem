package poj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p2976
{
    private static int N, K;
    private static int MAX_N = 1000 + 16;
    private static long[] a = new long[MAX_N];
    private static long[] b = new long[MAX_N];
    private static double[] y = new double[MAX_N];
    private static double INF = 200;

    private static boolean C(double k)
    {
        for (int i = 0; i < N; i++)
        {
            y[i] = 100 * a[i] - k * b[i];
        }

        Arrays.sort(y, 0, N);

        double sum = 0;
        for (int i = 0; i < K; i++)
        {
            sum += y[N - i - 1];
        }

        return sum >= 0;
    }

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();

        while (true)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            if (N == 0 && k == 0) break;
            K = N - k;
            StringTokenizer s1 = new StringTokenizer(br.readLine());
            StringTokenizer s2 = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++)
            {
                a[i] = Integer.parseInt(s1.nextToken());
                b[i] = Integer.parseInt(s2.nextToken());
            }

            double lb = 0, ub = INF;
            for (int i = 0; i < 100; i++)
            {
                double mid = (ub + lb) / 2;
                if (C(mid)) lb = mid;
                else        ub = mid;
            }
            ans.append(String.format("%d\n", Math.round(ub)));
        }
        System.out.print(ans);
    }
}
