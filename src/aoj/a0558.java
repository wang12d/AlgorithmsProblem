package aoj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class a0558 {
    private static int row, col;
    private static char[][] chars;
    private static int[][] dis;
    private static int sx, sy;
    private static int[] x_mov = {-1, 0, 1, 0}, y_mov = {0, -1, 0, 1};
    private static class Pair {
        private int x;
        private int y;
        private Pair(int x, int y) {this.x = x; this.y = y;}
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        row = in.nextInt();
        col = in.nextInt();
        int n = in.nextInt();
        chars = new char[row][];
        dis = new int[row][col];
        for (int i = 0; i < row; i++) {
            chars[i] = in.next().toCharArray();
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (chars[i][j] == 'S') {sx = i; sy = j;}
            }
        }
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            answer += bfs(sx, sy, i);
        }
        System.out.println(answer);
    }
    private static int bfs(int x, int y, int health) {
        Queue<Pair> q = new LinkedList<>();
        int INF = 1000000;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == x && j == y) dis[i][j] = 0;
                else dis[i][j] = INF;
            }
        }
        q.add(new Pair(x, y));
        while (!q.isEmpty()) {
            Pair start = q.peek(); q.poll();
            if (Character.isDigit(chars[start.x][start.y]) && Character.getNumericValue(chars[start.x][start.y]) == health) {
                sx = start.x; sy = start.y;
                break;
            }
            for (int i = 0; i < 4; i++) {
                int nx = start.x + x_mov[i], ny = start.y + y_mov[i];
                if (0 <= nx && nx < row && 0 <= ny && ny < col &&
                        chars[nx][ny] != 'X' && dis[nx][ny] == INF) {
                    q.add(new Pair(nx, ny));
                    dis[nx][ny] = dis[start.x][start.y] + 1;
                }
            }
        }
        return dis[sx][sy];
    }
}
