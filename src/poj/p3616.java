package poj;

import java.util.*;

public class p3616
{
    private static class Interval implements Comparable<Interval>
    {
        private int start;
        private int end;
        private int efficiency;

        private Interval(int start, int end, int efficiency)
        {
            this.start = start;
            this.end = end;
            this.efficiency = efficiency;
        }

        public int compareTo(Interval that)
        {
            return Integer.valueOf(this.start).compareTo(Integer.valueOf(that.start));
        }
    }

    private static final int MAX_M = (int) 1e3;
    private static int[] dp = new int[MAX_M+1]; // 定义dp[i]为0~i-1的时间段里面产奶量最大的时间段
    private static Interval[] intervals = new Interval[MAX_M+1];
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int m = in.nextInt();
        int r = in.nextInt();

        for (int i = 0; i < m; i++)
        {
            intervals[i] = new Interval(in.nextInt(), in.nextInt(), in.nextInt());
            intervals[i].end += r;
        }

        Arrays.sort(intervals, 0, m);

        for (int i = 0; i < m; i++)
        {
            dp[i] = intervals[i].efficiency;
            for (int j = 0; j < i; j++)
            {   // 找出前i-1个里面产量最大的
                if (intervals[i].start >= intervals[j].end)
                    dp[i] = Math.max(dp[i], dp[j] + intervals[i].efficiency);
            }
        }

        int max = 0;
        for (int i = 0; i < m; i++)
            max = Math.max(max, dp[i]);

        System.out.println(max);
    }
}
