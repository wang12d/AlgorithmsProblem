package poj;

import java.io.*;
import java.util.Arrays;

public class p3579
{
    private static int[] X = new int[100000 + 16];
    private static int N;
    private static int CN;
    private static int k;

    private static int lowBound(int lb, int ub, int val)
    {
        while (lb <= ub)
        {
            int mid = (ub + lb) / 2;
            if (X[mid] >= val)  ub = mid - 1;
            else                lb = mid + 1;
        }
        return ub;
    }

    private static boolean C(int mid)
    {
        long total = 0;
        for (int i = 1; i < N; i++)
        {
            total += i - lowBound(0, i, X[i] - mid) - 1;
        }
        return total >= k;
    }

    public static void main(String[] args) throws IOException
    {
        StreamTokenizer in = new StreamTokenizer(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF)
        {
            N = (int) in.nval;
            CN = (N - 1) * N / 2;
            k = (CN % 2 == 0) ? CN / 2 : (CN + 1) / 2;
            for (int i = 0; i < N; i++)
            {
                in.nextToken();
                X[i] = (int) in.nval;
            }

            Arrays.sort(X, 0, N);

            int lb = 0, ub = X[N - 1] - X[0];
            while (lb <= ub)
            {
                int mid = (ub + lb) / 2;
                if (C(mid))     ub = mid - 1;
                else            lb = mid + 1;
            }
            out.println(lb);
            out.flush();
        }
    }
}
