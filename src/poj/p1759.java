package poj;

import java.util.Scanner;

public class p1759
{
    private static int MAX_N = 1000 + 16;
    private static double MAX_A = 1000.0;
    private static double B;
    private static int N;
    private static double[] lamps = new double[MAX_N];

    private static boolean C(double mid)
    {
        lamps[1] = mid;
        for (int i = 2; i < N; i++)
        {
            lamps[i] = 2 * lamps[i-1] + 2 - lamps[i - 2];
            if (lamps[i] < 0)
            {
                return false;
            }
        }
        B = lamps[N-1];
        return true;
    }

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        lamps[0] = in.nextDouble();

        double lb = 0.0, ub = MAX_A;
        for (int i = 0; i < 100; i++)
        {
            double mid = (lb + ub) / 2;
            if (C(mid))     ub = mid;
            else            lb = mid;
        }

        System.out.printf("%.2f", B);
    }
}
