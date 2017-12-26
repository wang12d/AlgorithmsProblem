package poj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p3104
{
    private static int N, M;
    private static int MAX_N = 100000 + 16;
    private static int[] clothes = new int[MAX_N];

    private static boolean C(int d)
    {
        int total = 0;

        for (int i = 0; i < N; i++)
        {
            int k = clothes[i] - d;
            if (k <= 0) continue;
            total += (k + M - 1) / M;
            if (total > d)  return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
        {
            clothes[i] = Integer.parseInt(st.nextToken());
        }
        M = Integer.parseInt(br.readLine());
        M--;
        if (M == 0)
        {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < N; i++)
            {
                max = Math.max(max, clothes[i]);
            }
            System.out.println(max);
        }
        else
        {
            int lb = 0, ub = Integer.MAX_VALUE;
            while (ub - lb > 1)
            {
                int mid = (ub + lb) / 2;
                if (C(mid))         ub = mid;
                else                lb = mid;
            }
            System.out.println(ub);
        }
    }
}
