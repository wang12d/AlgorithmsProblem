package poj;

import java.util.Scanner;

public class p3009 {
    private static int row;
    private static int col;
    private static int[][] blocks;
    private static int INF = 100000;
    private static int answer = INF;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            col = input.nextInt();
            row = input.nextInt();
            if (row == 0 && col == 0) {
                input.close();
                break;
            }
            blocks = new int[row][col];
            int init_x = 0, init_y = 0, num;   // the location of start
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    num = input.nextInt();
                    blocks[i][j] = num;
                    if (num == 2) {
                        init_x = i; init_y = j;
                        blocks[i][j] = 0;
                    }
                }
            }
            dfs(init_x, init_y, 0);
            if (answer == INF) answer = -1;
            System.out.println(answer);
            answer = INF;
        }
    }
    private static void dfs(int x, int y, int times) {
        int[] x_mov = {-1, 0, 0, 1}, y_mov = {0, -1, 1, 0};
        int dx, dy;
        for (int i = 0; i < x_mov.length; i++) {
            dx = x + x_mov[i]; dy = y + y_mov[i];
            if (times + 1 > 10) return;
            while (0 <= dx && dx < row && 0 <= dy && dy < col &&
                    blocks[dx][dy] == 0) {
                dx += x_mov[i];
                dy += y_mov[i];
            }
            if (0 <= dx && dx < row && 0 <= dy && dy < col && blocks[dx][dy] == 3) {
                if (answer > times + 1) answer = times + 1;
                return;
            }
            if (0 <= dx && dx < row && 0 <= dy && dy < col && (Math.abs(dx - x) > 1 || Math.abs(dy - y) > 1)) {
                blocks[dx][dy] = 0;
                dfs(dx - x_mov[i], dy - y_mov[i], times+1);   // 遍历其中一个方向
                blocks[dx][dy] = 1;   // 遍历完之后将其变回来， 不然会影响到后续的结果
            }
        }
    }
}
