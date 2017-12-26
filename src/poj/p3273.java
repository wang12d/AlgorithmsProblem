package poj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p3273
{
    private static int MAX_N = 100000 + 16;
    private static int INF = Integer.MAX_VALUE;
    private static int[] days = new int[MAX_N];
    private static int N, M;

    private static boolean C(int d)
    {
        int last = 0;
        for (int i = 1; i <= M; i++)
        {
            int crt = last + 1;
            while (crt <= N && days[crt] - days[last] < d)
            {
                crt++;
            }
            if (crt == N + 1)   return false;
            last = crt - 1;
        }
        return true;
    }

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= N; i++)
        {
            days[i] = Integer.parseInt(br.readLine());
            days[i] = days[i] + days[i-1];  // 连续月数之和，做差
        }

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
