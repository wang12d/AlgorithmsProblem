package poj;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class p2376 {
    private static final Comparator<int[]> arrayComparator = new Comparator<int[]>() {
        @Override
        public int compare(int[] o1, int[] o2) {
            return Integer.compare(o1[0], o2[0]);
        }
    };
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int T = in.nextInt();
        int[][] nums = new int[N][2];
        for (int i = 0; i < N; i++) {
            nums[i][0] = in.nextInt();
            nums[i][1] = in.nextInt();
        }
        Arrays.sort(nums, arrayComparator);
        int k = solve(nums, T);
        System.out.println(k);
    }
    private static int solve(int[][] nums, int T) {
        int begin = 0, end = 0, ind = 0, count = 0;
        boolean flag = false;
        while (end < T) {
            begin = end + 1;
            for (int i = ind; i < nums.length; i++) {
                if (nums[i][0] <= begin) {
                    if (nums[i][1] > end) end = nums[i][1];
                }
                else {
                    ind = i;
                    break;
                }
            }
            if (begin > end) return -1;
            else count++;
        }
        return count;
    }
}
