package poj;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class p3484
{
    private static final int MAX_N = 1000000;
    private static long[] X = new long[MAX_N], Y = new long[MAX_N], Z = new long[MAX_N];
    private static long[] C = new long[MAX_N];
    private static int N;
    private static ArrayList<String> strings = new ArrayList<String>();

    private static long countSum(long mid)
    {
        long sum = 0;
        for (int i = 0; i < N; i++)
        {
            if (mid >= Y[i])
            {
                sum += C[i];
            }
            else if (mid >= X[i])
            {
                sum += (mid - X[i]) / Z[i] + 1;
            }
        }
        return sum;
    }

    private static void solve()
    {
        N = 0;
        for (String s: strings)
        {
            StringTokenizer st = new StringTokenizer(s);
            X[N] = Integer.parseInt(st.nextToken());
            Y[N] = Integer.parseInt(st.nextToken());
            Z[N] = Integer.parseInt(st.nextToken());
            N++;
        }

        long last = 0;
        for (int i = 0; i < N; i++)
        {
            C[i] = (Y[i] - X[i]) / Z[i] + 1;
            last ^= C[i];
        }

        if ((last & 1) == 0)
        {
            System.out.println("no corruption");
        }
        else
        {
            long lb = 0, ub = Long.MAX_VALUE;
            while (ub - lb > 1)
            {
                long mid = (lb + ub) / 2;
                if (countSum(mid) % 2 == 0)
                {
                    lb = mid;
                }
                else
                {
                    ub = mid;
                }

            }
            System.out.println(ub + " " + (countSum(ub) - countSum(ub - 1)));
        }
    }

    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);

        String string= null;
        while (scan.hasNext())
        {
            while (scan.hasNext() && !(string=scan.nextLine()).equals("")) strings.add(string);
            solve();    strings.clear();
            while (scan.hasNext() && (string=scan.nextLine()).equals(""));
            strings.add(string);
        }
    }
}