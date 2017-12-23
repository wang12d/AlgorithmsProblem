package poj;


import java.util.*;

public class p3050 {
    private static Set<Integer> set = new HashSet<>();
    private static int[][] nums = new int[5][5];
    private static int[] x_mov = {-1, 0, 1, 0}, y_mov = {0, -1, 0, 1};
    private static class Pair {
        private final int x;
        private final int y;
        private Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                nums[i][j] = in.nextInt();
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                solve(0, i, j, nums[i][j]);
            }
        }
        System.out.println(set.size());
    }
    private static void solve(int k, int i, int j, int str) {
        if (k == 5) {
            set.add(str);
            return;
        }

        for (int v = 0; v < 4; v++) {
            int dx = i + x_mov[v], dy = j + y_mov[v];
            if (0 <= dx && dx < 5 && 0 <= dy && dy < 5) {
                solve(k+1, dx, dy, str*10+nums[dx][dy]);
            }
        }
    }
}
