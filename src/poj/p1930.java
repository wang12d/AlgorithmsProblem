package poj;

import java.io.*;

public class p1930
{
    private static long gcd(long n, long m)
    {
        if (0 == m) return n;
        return gcd(m, n % m);
    }
    public static void main(String args[]) throws IOException
    {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();

        while (true)
        {
            String[] string = br.readLine().split("\\.+");
            if (string.length == 1) break;
            long minA = Long.MAX_VALUE, minK = Long.MAX_VALUE;
            for (int i = 1; i <= string[1].length(); i++)
            {
                long a = Long.parseLong(string[1]);
                long c = (long) Math.pow(10, i);
                long b = a / c;
                a -= b;
                long k = (long) ((Math.pow(10, i)-1) * Math.pow(10, string[1].length() - i));
                long common = gcd(a, k);
                a /= common;
                k /= common;
                minA = Math.min(a, minA);
                minK = Math.min(k, minK);
            }
            ans.append(String.valueOf(minA));
            ans.append("/");
            ans.append(String.valueOf(minK));
            ans.append("\n");
        }
        bw.write(ans.toString());
        bw.close();
    }
}
