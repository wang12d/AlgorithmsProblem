package poj;

import java.util.*;

public class p3421
{
    private static TreeSet<Integer> primes = new TreeSet<>();

    private static void setPrimes()
    {
        int[] number = new int[(1 << 20) + 1];
        number[0] = 1;
        number[1] = 1;
        for (int i = 2; i < number.length; i++)
        {
            if (number[i] == 0)
            {
                primes.add(i);
                for (int j = i*i; j < number.length; j += i)
                {
                    if (j < 0) break;
                    number[j] = 1;
                }
            }
        }
    }

    private static void solve(int s)
    {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int p: primes)
        {
            if (p > s) break;
            int count = 0;
            while (s % p == 0)
            {
                s /= p;
                count++;
            }
            if (count != 0)
            {
                map.put(p, count);
            }
        }
        compute(map);
    }

    private static void compute(HashMap<Integer, Integer> map)
    {
        int total = 0;
        for (int i: map.keySet())
        {
            total += map.get(i);
        }
        long h = factorial(total);
        for (int i: map.keySet())
        {
            h /= factorial(map.get(i));
        }
        System.out.printf("%d %d\n", total, h);
    }

    private static long factorial(int k)
    {
        long s = 1;
        for (int i = 2; i <= k; i++)
        {
            s *= i;
        }
        return s;
    }
    public static void main(String[] args)
    {
        setPrimes();
        Scanner in = new Scanner(System.in);
        while (in.hasNext())
        {
            solve(in.nextInt());
        }
    }
}
