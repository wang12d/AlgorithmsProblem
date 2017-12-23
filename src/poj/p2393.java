package poj;
import java.util.Scanner;
public class p2393 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(), S = in.nextInt();
        int[][] nums = new int[N][2];
        for (int i = 0; i < N; i++) {
            nums[i][0] = in.nextInt();
            nums[i][1] = in.nextInt();
        }
        long cost = solve(nums, S);
        System.out.println(cost);
    }
    public static long solve(int[][] nums, int S) {
        long fees = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            while (i < nums.length  - 1 && (nums[i][0] + S) < nums[i+1][0]) {
                nums[i+1][0] = nums[i][0] + S;
                i++;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            fees += nums[i][0]*nums[i][1];
        }
        return fees;
    }
}
