package poj;

import java.util.*;

public class p3280
{
    private static final int MAX_M = 2000;
    private static final int MAX_N = 26;
    private static int[][] dp = new int[MAX_M+1][MAX_M+1];  // dp[i][j]表示将S[i...j+1]变成回文的成本
    private static int[] add = new int[MAX_N];  // 增加的费用
    private static int[] delete = new int[MAX_N];   // 删除的费用
    private static String S;  // 输入的字符串

    private static int solve(int i, int j)
    {
        if (dp[i][j] != -1)
            return dp[i][j];

        if (i >= j)
            return dp[i][j] = 0;

        dp[i][j] = Math.min(solve(i+1, j) + Math.min(add[S.charAt(i) - 'a'], delete[S.charAt(i) - 'a']),
                solve(i, j-1) + Math.min(add[S.charAt(j) - 'a'], delete[S.charAt(j) - 'a']));

        if (S.charAt(i) == S.charAt(j))
            dp[i][j] = Math.min(dp[i][j], solve(i+1, j-1));

        return dp[i][j];
    }

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int m = in.nextInt();
        S = in.next();

        for (int i = 0; i < m; i++)
            Arrays.fill(dp[i], 0, m, -1);

        for (int i = 0; i < n; i++)
        {
            char x = in.next().charAt(0);
            add[x - 'a'] = in.nextInt();
            delete[x - 'a'] = in.nextInt();
        }

        int cost = solve(0, m-1);

        System.out.println(cost);
    }
}
