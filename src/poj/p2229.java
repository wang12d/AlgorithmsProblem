package poj;

import java.util.*;

public class p2229
{
    private static final int MAX_N = (int) 1e6;
    private static final int CONST = (int) 1e9;
    private static final int[] dp = new int[MAX_N+1];    // 用来保存方式，0表示偶数，1表示奇数

    // n表示要计算的数，k表示2^k
    // dp[n] = dp[n-1]+dp[n/2] (n % 2 == 0)
    // dp[n] = dp[n-1]+1       (n % 2 == 1)
    // dp[0] = 1
    private static void solve()
    {
        dp[0] = 1;
        for (int i = 1; i <= MAX_N; i++)
        {
            if (i % 2 == 0)
            {
                dp[i] = (dp[i/2] % CONST);
            }
            dp[i] += (dp[i-1] % CONST);
        }
    }

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        solve();
        int n = in.nextInt();

        System.out.println(dp[n]);
    }
}
