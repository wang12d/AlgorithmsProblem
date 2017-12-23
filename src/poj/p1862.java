package poj;
import java.util.*;
public class p1862
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        double[] arrs = new double[N];
        for (int i = 0; i < N; i++)
        {
            arrs[i] = in.nextDouble();
        }
        Arrays.sort(arrs);
        double answer = arrs[N - 1];
        for (int i = arrs.length - 2; i >= 0; i--)
        {
            answer = 2 * Math.sqrt(arrs[i] * answer);
        }
        System.out.printf("%.3f\n", answer);
    }
}
