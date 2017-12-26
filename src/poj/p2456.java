package poj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p2456
{
    private static final int MAX_N = 100000 + 16;
    private static int M;
    private static int N;
    private static final int INF = Integer.MAX_VALUE;
    private static final int[] stalls = new int[MAX_N];

    private static boolean C(int d)
    {
        int last = 0;
        for (int i = 1; i < M; i++)
        {
            int crt = last + 1;
            // 所有的兽栏还没用光，且不满足条件
            while (crt < N && stalls[crt] - stalls[last] < d)
            {
                crt++;
            }
            // 兽栏以用光，不满足条件
            if (crt == N)   return false;
            last = crt; // 满足条件，下一个
        }
        return true;
    }

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++)
        {
            stalls[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(stalls, 0, N);
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
