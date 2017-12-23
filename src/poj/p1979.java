package poj;

import java.util.ArrayList;
import java.util.Scanner;

public class p1979 {
    private static int n;
    private static char[][] tiles;
    private static int N;
    private static int M;
    private static ArrayList<Integer> answer = new ArrayList<>();
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            N = input.nextInt();
            M = input.nextInt();
            tiles = new char[M][N];
            for (int i = 0; i < M; i++) {
                String str = input.next();
                for (int j = 0; j < N; j++) {
                    tiles[i][j] = str.charAt(j);
                }
            }
            solve();
            if (!(N == 0 && M == 0)) answer.add(n);
            n = 0;
        }
        input.close();
         for (int i: answer) {
            System.out.println(i);
         }
    }
    private static void solve() {
        for (int row = 0; row < M; row++) {
            for (int col = 0; col < N; col++) {
                if (tiles[row][col] == '@') {
                    dfs(row, col);
                }
            }
        }
    }
    private static void dfs(int x, int y) {
        tiles[x][y] = '#';
        n++;
        int[] hor = {1,-1};
        int dx, dy;
        for (int i: hor) {
            dx = x + i;
            dy = y + i;
            if (dx >= 0 && dx < M && y >= 0 && y < N &&
                    tiles[dx][y] == '.')
                dfs(dx, y);
            if (dy >= 0 && dy < N && x >= 0 && x < M &&
                    tiles[x][dy] == '.')
                dfs(x, dy);
        }
    }
}
