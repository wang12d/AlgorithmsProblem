package aoj;

import java.util.Arrays;
import java.util.Scanner;

public class a2200
{
    private static final int INF = (int) 1e8;
    public static void floyd(int[][] dl, int[][] ds)
    {
        int n = dl.length;
        for (int k = 0; k < n; k++)
        {
            for (int i = 0; i < n; i++)
            {
                for (int j = 0; j < n; j++)
                {
                    dl[i][j] = Math.min(dl[i][j], dl[i][k]+dl[k][j]);
                    ds[i][j] = Math.min(ds[i][j], ds[i][k]+ds[k][j]);
                }
            }
        }
    }

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        while (true)
        {
            int n = in.nextInt(), m = in.nextInt();
            if (n == 0 && m == n)
            {
                break;
            }
            int[][] dp = new int[1024][n];
            int[][] dl = new int[n][n];
            int[][] ds = new int[n][n];

            for (int i = 0; i < n; i++)
            {
                Arrays.fill(dl[i], INF);
                Arrays.fill(ds[i], INF);
            }


            for (int i = 0; i < n; i++)
            {
                ds[i][i] = 0;
                dl[i][i] = 0;
            }
            for (int i = 0; i < m; i++)
            {
                int v = in.nextInt(), w = in.nextInt(), t = in.nextInt();
                String c = in.next();
                v--;w--;
                if (c.equals("L"))
                {
                    dl[v][w] = Math.min(dl[v][w], t);
                    dl[w][v] = dl[v][w];
                }
                else
                {
                    ds[v][w] = Math.min(ds[v][w], t);
                    ds[w][v] = ds[v][w];
                }
            }
            floyd(dl, ds);
            int r = in.nextInt();
            int[] nums = new int[r];
            for (int i = 0; i < r; i++)
            {
                nums[i] = in.nextInt();
                nums[i]--;
            }
            for (int i = 0; i < r; i++)
            {
                for (int j = 0; j < n; j++)
                {
                    dp[i][j] = INF;
                }
            }
            for (int i = 0; i < n; i++)
            {
                dp[0][i] = ds[nums[0]][i] + dl[i][nums[0]];
            }

            for (int i = 1; i < r; i++)
            {
                for (int j = 0; j < n; j++)
                {
                    for (int k = 0; k < n; k++)
                    {
                        if (j != k)
                        {
                            dp[i][k] = Math.min(dp[i][k], dp[i-1][j] + dl[nums[i-1]][j] + ds[j][k] + dl[k][nums[i]]);
                        }
                        else
                        {
                            dp[i][k] = Math.min(dp[i][k], dp[i-1][j]+dl[nums[i-1]][nums[i]]);
                        }
                    }
                }
            }

            int cost = INF;
            for (int i = 0; i < n; i++)
            {
                cost = Math.min(dp[r-1][i], cost);
            }

            System.out.println(cost);
        }
    }
}
