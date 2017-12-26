package poj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p3258
{
    private static final int MAX_N = 50000 + 16;
    private static int N, M;
    private static int[] rocks = new int[MAX_N];
    private static final int INF = Integer.MAX_VALUE;

    private static boolean C(int d)
    {
        int last = 0;
        for (int i = 1; i < M; i++)
        {
            int crt = last + 1;
            while (crt <= N + 1 && rocks[crt] - rocks[last] < d)
            {
                crt++;
            }
            if (crt == N + 2)   return false;
            last = crt;
        }
        return true;
    }

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        rocks[N+1] = k;
        int m = Integer.parseInt(st.nextToken());
        M = N - m + 2;  // 需要保留的石头数量
        for (int i = 1; i <= N; i++)
        {
            rocks[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(rocks, 0, N+2);

        int lb = 0, ub = INF;
        while (ub - lb > 1)
        {
            int mid = (ub + lb) / 2;
            if (C(mid))
            {
                lb = mid;
            }
            else
            {
                ub = mid;
            }
        }
        System.out.println(lb);
    }
}
