package poj;

import java.util.*;

public class p2429
{
    private static long gcd(long a, long b)
    {
        if (0 == b) return a;
        return gcd(b, a%b);
    }

    public static void main(String[] args)
    {

    }
}
