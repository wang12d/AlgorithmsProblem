package poj;

import java.io.*;

public class p3292
{
    private static final int MAX_M = 1000001;
    private static int[] hPrimes  = new int[MAX_M+1];
    private static int[] hSemiPrimes = new int[MAX_M+1];
    private static int[] acc = new int[MAX_M+1];
    private static int count;
    private static void init()
    {
        int[] number = new int[MAX_M+1];
        number[0] = 1;
        number[1] = 1;
        for (int i = 5; i <= MAX_M; i += 4)
        {
            if (number[i] == 0)
            {
                hPrimes[count++] = i;
                // 如果i是h-数，那么5*i+4*i*x也是h-数
                for (int j = 5*i; j <= MAX_M; j += i*4)
                {
                    if (j < 0) break;
                    number[j] = 1;
                }
            }
        }

        for (int i = 0; i < count; i++)
        {
            for (int j = 0; j < i + 1; j++)
            {
                int w = hPrimes[i] * hPrimes[j];
                if (w > MAX_M || w < 0)
                {
                    break;
                }
                hSemiPrimes[w] = 1;
            }
        }
        for (int i = 1; i < MAX_M; i++)
        {
            acc[i] = acc[i-1] + hSemiPrimes[i];
        }

    }

    private static String solve(int s)
    {
        return String.format("%d %d\n", s, acc[s]);
    }

    public static void main(String[] args) throws IOException
    {
        init();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        while (true)
        {
            int s = Integer.parseInt(br.readLine());
            if (s == 0) break;
            sb.append(solve(s));
        }
        bw.write(sb.toString());
        bw.close();
    }
}
