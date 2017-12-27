package poj;

import java.util.Scanner;

public class p3685
{
    private static long f(long i, long j)
    {
        return i*i + 100000*i + j*j - 100000*j + i*j;
    }

    private static boolean C(long mid, long n, long m)
    {
        long k = 0;
        for (int j = 1; j <= n; j++)
        {
            long lb = 0, ub = n + 1;
            while (ub - lb > 1)
            {
                long i = (ub + lb) / 2;
                if (f(i, j) >= mid)     ub = i;
                else                    lb = i;
            }
            k += lb;
        }
        return k < m;
    }

    private static void solve(long n, long m)
    {
        long lb = -100000 * n, ub = 3*n*n + 100000*n;
        while (ub - lb > 1)
        {
            long mid = (ub + lb) / 2;
            if (C(mid, n, m))     lb = mid;
            else                  ub = mid;
        }

        System.out.println(lb);
    }

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int i = 0; i < t; i++)
        {
            solve(in.nextLong(), in.nextLong());
        }
    }
}
