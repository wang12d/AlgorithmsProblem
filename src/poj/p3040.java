package poj;
import java.util.*;
public class p3040 {
    private static final Comparator<int[]> arrayComparator = new Comparator<int[]>()
    {
        @Override
        public int compare(int[] o1, int[] o2)
        {
            return Integer.compare(o1[0], o2[0]);
        }
    };
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(), S = in.nextInt();
        int[][] arrs = new int[N][2];
        for (int i = 0; i < N; i++)
        {
            arrs[i][0] = in.nextInt();
            arrs[i][1] = in.nextInt();
        }
        Arrays.sort(arrs, arrayComparator);
        int count = 0;
        while (solve(arrs, S)) count++;
        System.out.println(count);
    }

    /**
     * 每周给S费用，能够给的最大周数。维护两个指针，从开头到末尾。如果最大的硬币大于S
     * 则直接给。否则，用最小的加最大的，直到其大于等于S。
     * @param arrs
     * @param S
     * @return
     */
    private static boolean solve(int[][] arrs, int S)
    {
        for (int i = arrs.length - 1; i >= 0 ; i--)
        {
            int spend = Math.min(arrs[i][1], S / arrs[i][0]);
            if (spend == 0) break;
            arrs[i][1] -= spend;
            S -= spend * arrs[i][0];
            if (S <= 0) return true;
        }
        for (int i = 0; i < arrs.length; i++)
        {
            int spend = Math.min(arrs[i][1], S / arrs[i][0] + (S % arrs[i][0] == 0 ? 0 : 1));
            if (spend == 0) break;
            arrs[i][1] -= spend;
            S -= spend * arrs[i][0];
            if (S <= 0) return true;
        }
        return S <= 0;
    }
}
