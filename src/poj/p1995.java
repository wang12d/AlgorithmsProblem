package poj;

import java.io.*;
import java.util.StringTokenizer;

public class p1995
{
    private static long fastMod(long a, long b, long m)
    {
        long result = 1;
        while (b > 0)
        {
            if ((b & 1) == 1)
            {
                result = (result * a) % m;
            }
            a = (a * a) % m;
            b >>= 1;
        }
        return result;
    }

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder ans = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++)
        {
            int m = Integer.parseInt(br.readLine());
            int k = Integer.parseInt(br.readLine());
            long v = 0;
            for (int j = 0; j < k; j++)
            {
                StringTokenizer st = new StringTokenizer(br.readLine());
                long a = Long.parseLong(st.nextToken());
                long b = Long.parseLong(st.nextToken());
                v += fastMod(a, b, m);
            }
            ans.append(String.format("%d\n", v % m));
        }

        bw.write(ans.toString());
        bw.close();
    }
}
