package poj;

import java.io.*;
import java.util.StringTokenizer;

public class p3641
{
    private static boolean fastMod(long base, long exp)
    {
        long p = exp;
        long result = 1;
        long a = base;
        while (exp > 0)
        {
            if ((exp & 1) == 1)
            {
                result = (result * base) % p;
            }
            exp >>= 1;
            base = (base * base) % p;
        }
        return result == a;
    }

    private static boolean isPrime(long number)
    {
        if (number < 2) return false;
        for (int i = 2; i < Math.sqrt(number); i += 1)
        {
            if (number % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException
    {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();
        while (true)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long exp = Long.parseLong(st.nextToken()), base = Long.parseLong(st.nextToken());
            if (exp == 0 && base == 0) break;
            if (fastMod(base, exp) && !isPrime(exp))
            {
                ans.append("yes\n");
            }
            else
            {
                ans.append("no\n");
            }
        }
        bw.write(ans.toString());
        bw.close();
    }
}
