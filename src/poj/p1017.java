package poj;

import java.util.Scanner;

public class p1017
{
    private static int[] p3 = {0, 5, 3, 1};         // p3能够装的2*2的数量
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        int[] inputs = new int[6];
        while (true) {
            for (int i = 0; i < 6; i++)
            {
                inputs[i] = in.nextInt();
            }
            if (sum(inputs) == 0) break;
            System.out.println(numberOfBoxes(inputs));
        }
        in.close();
    }
    private static int numberOfBoxes(int[] arrs)
    {
        int n = arrs[3] + arrs[4] + arrs[5] + (int) Math.ceil(arrs[2] / 4.0);
        int numOfTwo = 5 * arrs[3] + p3[arrs[2] % 4];     // 已有的箱子能够装下2*2的数量
        if (numOfTwo < arrs[1])
        {
            n += (int) Math.ceil((arrs[1] - numOfTwo) / 9.0);
        }
        int numOfOne = 36 * n - 36 * arrs[5] - 25 * arrs[4] - 16 * arrs[3] - 9 * arrs[2] - 4 * arrs[1];     // 所有箱子装完后剩下的空间
        if (numOfOne < arrs[0])
        {
            n += (int) Math.ceil((arrs[0] - numOfOne) / 36.0);
        }
        return n;
    }
    private static int sum(int[] arrs)
    {
        int sum = 0;
        for (int i = 0; i < arrs.length; i++)
        {
            sum += arrs[i];
        }
        return sum;
    }
}
