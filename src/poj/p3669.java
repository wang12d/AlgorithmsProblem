package poj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class p3669 {
    private static int N = 303;
    private static int[][] dis = new int[N][N];
    private static int[][] stats = new int[N][N];
    private static int[] x_mov = {-1, 0, 1, 0}, y_mov = {0, -1, 0, 1};
    private static int INF = 1000000;
    private static class Pair {
        private int x;
        private int y;
        private Pair(int x, int y) {this.x = x; this.y = y;}
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] meteor = new int[n][3];
        for (int i = 0; i < n; i++) {
            meteor[i][0] = in.nextInt();
            meteor[i][1] = in.nextInt();
            meteor[i][2] = in.nextInt();
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                stats[i][j] = -1;
                dis[i][j] = INF;
            }
        }
        int x, y, dx, dy;
        for (int i = 0; i < n; i++) {
            x = meteor[i][0]; y = meteor[i][1];
            if (stats[x][y] == -1) {stats[x][y] = meteor[i][2];}
            else {stats[x][y] = Math.min(stats[x][y], meteor[i][2]);}
            for (int j = 0; j < 4; j++) {
                dx = x + x_mov[j]; dy = y + y_mov[j];
                if (0 <= dx && dx < 301 && 0 <= dy && dy < 301) {
                    if (stats[dx][dy] == -1) {stats[dx][dy] = meteor[i][2];}
                    else {stats[dx][dy] = Math.min(stats[dx][dy], meteor[i][2]);}
                }
            }
        }
        int times = bsf();
        System.out.println(times);
    }
    private static int bsf() {
        Queue<Pair> q = new LinkedList<Pair>();
        q.add(new Pair(0, 0));
        dis[0][0] = 0;
        int dx, dy;
        while (!q.isEmpty()) {
            Pair loc = q.peek(); q.poll();
            if (stats[loc.x][loc.y] == -1) {
                return dis[loc.x][loc.y];
            }
            for (int i = 0; i < 4; i++) {
                dx = loc.x + x_mov[i]; dy = loc.y + y_mov[i];
                if (0 <= dx && dx < N && 0 <= dy && dy < N &&
                        dis[dx][dy] == INF &&
                        (stats[dx][dy] == -1 || dis[loc.x][loc.y] + 1 < stats[dx][dy])) {
                    q.add(new Pair(dx, dy));
                    dis[dx][dy] = dis[loc.x][loc.y] + 1;
                }
            }
        }
        return -1;
    }
}
