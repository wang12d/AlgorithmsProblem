package poj;

import java.util.*;

public class p3126
{
    private static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    private static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
    private static ArrayList<Integer> primes = new ArrayList<Integer>();

    private static void init()
    {
        int max = 10000;
        int[] numberList = new int[max+1];
        numberList[0] = 1;
        numberList[1] = 1;
        for (int i = 2; i <= max; i++)
        {
            if (numberList[i] == 0)
            {
                for (int j = i*i; j <= max; j+=i)
                {
                    numberList[j] = 1;
                }
            }
        }

        int count = 0;
        for (int i = 1000; i <= max; i++)
        {
            if (numberList[i] == 0)
            {
                primes.add(i);
                map.put(i, count++);
            }
        }
        for (int i = 0; i < count; i++)
        {
             adj.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < count; i++)
        {
            for (int j = i+1; j < count; j++)
            {
                int v = primes.get(i), w = primes.get(j);
                if (isPath(v, w))
                {
                    adj.get(i).add(j);
                    adj.get(j).add(i);
                }
            }
        }
    }

    private static void solve(int s, int t)
    {
        Queue<Integer> q = new LinkedList<Integer>();
        s = map.get(s);
        t = map.get(t);
        int[] disTo = new int[map.size()];
        boolean[] marked = new boolean[map.size()];
        q.add(s);
        disTo[s] = 0;
        marked[s] = true;

        while (!q.isEmpty())
        {
            int v = q.poll();
            if (v == t)
            {
                System.out.println(disTo[t]);
                return;
            }
            for (int i: adj.get(v))
            {
                if (!marked[i])
                {
                    disTo[i] = disTo[v] + 1;
                    q.add(i);
                    marked[i] = true;
                }
            }
        }
    }
    private static boolean isPath(int v, int w)
    {
        int count = 0;
        while (v != 0)
        {
            if (v % 10 != w % 10)
            {
                count++;
            }
            if (count > 1)
                return false;
            v /= 10; w /= 10;
        }
        return count == 1;
    }
    public static void main(String[] args)
    {
        init();
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int i = 0; i < t; i++)
        {
            solve(in.nextInt(), in.nextInt());
        }
    }
}
