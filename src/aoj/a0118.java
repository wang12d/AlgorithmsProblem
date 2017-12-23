package aoj;

import java.util.Scanner;

public class a0118 {
    private static char[][] chars;
    private static int row, col;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        char symbol;
        while (true) {
            row = input.nextInt();
            col = input.nextInt();
            if (row == 0 && col == 0) {
                break;
            }
            chars = new char[row][col];
            for (int i = 0; i < row; i++) {
                chars[i] = input.next().toCharArray();
            }
            int n = 0;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    symbol = chars[i][j];
                    if (symbol != '.') {
                        dfs(i, j, symbol);
                        n++;
                    }
                }
            }
            System.out.println(n);
        }
    }
    private static void dfs(int x, int y, char symbol) {
        chars[x][y] = '.';
        int dx, dy;
        int[] x_mov = {-1, 0, 0, 1}, y_mov = {0, -1, 1, 0};
        for (int i = 0; i < x_mov.length; i++) {
            dx = x + x_mov[i]; dy = y + y_mov[i];
            if (0 <= dx && dx < row && 0 <= dy && dy < col &&
                    chars[dx][dy] == symbol) {
                dfs(dx, dy, symbol);
            }
        }
    }
}
