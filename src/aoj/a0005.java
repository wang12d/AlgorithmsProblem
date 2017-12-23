package aoj;

import java.util.*;

public class a0005
{
    private static int gcd(int x, int y)
    {
        if (0 == y)
            return x;
        return gcd(y, x % y);
    }

    private static int lcm(int x, int y)
    {
        return x  / gcd(x, y) * y;
    }

    public static void main(String[] main)
    {
        Scanner in = new Scanner(System.in);

        while (in.hasNext())
        {
            int x = in.nextInt();
            int y = in.nextInt();

            System.out.print(gcd(x, y) + " " + lcm(x, y));
            System.out.println();
        }
    }
}
