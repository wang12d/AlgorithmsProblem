package poj;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class p2236
{
    private static class UF
    {
        private int[] id;
        private int[] sz;
        public UF(int n)
        {
            id = new int[n];
            sz = new int[n];
            for (int i = 0; i < n; i++)
            {
                id[i] = i;
                sz[i] = 1;
            }
        }
        public boolean connected(int p, int q)
        {
            return get(p) == get(q);
        }
        private int get(int p)
        {
            while (p != id[p])  p = id[p];
            return p;
        }
        public void union(int p, int q)
        {
            int pRoot = get(p);
            int qRoot = get(q);

            if (pRoot == qRoot) return;

            if (sz[pRoot] > sz[qRoot]) { id[qRoot] = pRoot; sz[pRoot] += sz[qRoot];}
            else                       { id[pRoot] = qRoot; sz[qRoot] += sz[pRoot];}
        }
    }

    private static class Computer
    {
        private final int x;
        private final int y;
        public boolean repaired;
        public Computer(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
        public double distanceTo(Computer that)
        {
            int x_diff = this.x - that.x;
            int y_diff = this.y - that.y;
            return Math.sqrt(x_diff * x_diff + y_diff * y_diff);
        }
    }

    public static void main(String[] args)
    {
        PrintStream out = new PrintStream(System.out);
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int d = in.nextInt();

        UF uf = new UF(n);
        Computer[] computers = new Computer[n];
        List<Integer> repaired = new ArrayList<>();

        for (int i = 0; i < n; i++)
        {
            computers[i] = new Computer(in.nextInt(), in.nextInt());
        }

        while (in.hasNext())
        {
            String ch = in.next();
            if (ch.equals("O"))
            {
                int k = in.nextInt();
                computers[k-1].repaired = true;
                repaired.add(k-1);
                for (int i: repaired)
                {
                    if (computers[k-1].distanceTo(computers[i]) <= d)
                    {
                        uf.union(i, k-1);
                    }
                }
            }
            else if (ch.equals("S"))
            {
                int v = in.nextInt();
                int t = in.nextInt();
                if (uf.connected(v-1, t-1))
                    out.println("SUCCESS");
                else
                    out.println("FAIL");
            }
        }
    }
}
