package poj;

import java.util.*;

public class p3176
{
    private static final int MAX_N = 350;
    private static int[][] dp = new int[MAX_N][MAX_N];

    private static void solve(int n)
    {
        for (int i = n - 2; i >= 0; i--)
        {
            for (int j = 0; j <= i; j++)
            {
                dp[i][j] = Math.max(dp[i+1][j], dp[i+1][j+1]) + dp[i][j];
            }
        }
        System.out.println(dp[0][0]);
    }

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j <= i; j++)
            {
                dp[i][j] = in.nextInt();
            }
        }
        solve(n);
    }
}
