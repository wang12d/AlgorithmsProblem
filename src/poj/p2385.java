package poj;

import java.util.*;

public class p2385
{
    private static final int MAX_N = 1000;
    private static final int MAX_M = 31;
    private static int[][][] dp = new int[MAX_N+1][MAX_M+1][2];   // xyz分别表示在第x+1个苹果移动不超过y+1的苹果数
    private static int[] apples = new int[MAX_N+1];

    private static int changeLoc(int loc)
    {
        return loc == 0 ? 1 : 0;
    }

    private static void solve(int n, int m)
    {
        if (apples[0] == 0)
            dp[0][0][0] = 1;    // 初始位置的地方有苹果
        else
            dp[0][1][1] = 1;    // 需要移动才能吃到苹果

        for (int i = 0; i < n-1; i++)
        {
            for (int j = 0; j <= m; j++)
            {
                for (int k = 0; k < 2; k++)
                {
                    if (k == apples[i+1])
                    {   // 原地不动，下一个苹果还是在这颗树下
                        dp[i+1][j][k] = Math.max(dp[i+1][j][k], dp[i][j][k]+1);
                        // 移动
                        dp[i+1][j+1][changeLoc(k)] = Math.max(dp[i+1][j+1][changeLoc(k)], dp[i][j][k]);
                    }
                    else
                    {   // 不移动，吃不到苹果
                        dp[i+1][j][k] = Math.max(dp[i+1][j][k], dp[i][j][k]);
                        // 移动去吃苹果,值为原来的值加一
                        dp[i+1][j+1][changeLoc(k)] = Math.max(dp[i+1][j+1][changeLoc(k)], dp[i][j][k]+1);
                    }
                }
            }
        }
    }

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        int t = in.nextInt();
        int w = in.nextInt();

        for (int i = 0; i < t; i++)
        {
            apples[i] = in.nextInt();
            apples[i]--;
        }

        solve(t, w);

        int max = 0;
        for (int i = 0; i < t; i++)
        {
            for (int j = 0; j <= w; j++)
            {
                for (int k = 0; k < 2; k++)
                {
                    max = Math.max(max, dp[i][j][k]);
                }
            }
        }

        System.out.println(max);
    }
}
