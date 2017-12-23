package aoj;

import java.util.Scanner;

public class a0033 {
    private static int[] balls = new int[10];
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        for (int j = 0; j < N; j++) {
            for (int i = 0; i < 10; i++) {
                balls[i] = input.nextInt();
            }
            if (solveBall(0, 0, 0))
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }
    private static boolean solveBall(int n, int leftRoot, int rightRoot) {
        if (n == 9 && (balls[n] > leftRoot || balls[n] > rightRoot)) return true;
        if (balls[n] < leftRoot && balls[n] < rightRoot) return false;
        if (balls[n] > leftRoot) {
            return solveBall(n+1, balls[n], rightRoot);
        }
        if (balls[n] > rightRoot) {
            return solveBall(n + 1, leftRoot, balls[n]);
        }
        return false;
    }
}
