package poj;

import java.util.*;
import java.io.PrintStream;

public class p3614
{
    private static class Pair implements Comparable<Pair>
    {
        private int x;      // x表示SPF
        private int y;      // y表示数量

        public Pair(int x, int y)
        {
            this.x = x;
            this.y = y;
        }

        public int compareTo(Pair that)
        {
            if (this.x > that.x) return +1;
            if (this.x < that.x) return -1;
            return 0;
        }
    }

    private static class Cow implements Comparable<Cow>
    {
        private int lowSPF;
        private int highSPF;

        public Cow(int lowSPF, int highSPF)
        {
            this.lowSPF = lowSPF;
            this.highSPF = highSPF;
        }

        public int compareTo(Cow that)
        {
            if (this.lowSPF > that.lowSPF) return +1;
            if (this.lowSPF < that.lowSPF) return -1;
            if (this.highSPF > that.highSPF) return +1;
            if (this.highSPF < that.highSPF) return -1;
            return 0;
        }
    }

    public static void main(String[] args)
    {
        PrintStream out = new PrintStream(System.out);
        Scanner in = new Scanner(System.in);

        int c = in.nextInt(), l = in.nextInt();

        Cow[] cows = new Cow[c];
        Pair[] pairs = new Pair[l];
        PriorityQueue<Integer> q = new PriorityQueue<>();

        for (int i = 0; i < c; i++)
        {
            cows[i] = new Cow(in.nextInt(), in.nextInt());
        }
        for (int i = 0; i < l; i++)
        {
            pairs[i] = new Pair(in.nextInt(), in.nextInt());
        }

        Arrays.sort(cows);
        Arrays.sort(pairs);

        int count = 0;
        int cowIndex = 0;
        for (int i = 0; i < l; i++)
        {
            while (cowIndex < c && cows[cowIndex].lowSPF <= pairs[i].x)
            {
                q.add(cows[cowIndex].highSPF);
                cowIndex++;
            }

            while (!q.isEmpty() && pairs[i].y > 0)
            {
                int highSPF = q.peek(); q.poll();
                if (highSPF >= pairs[i].x)
                {
                    pairs[i].y--;
                    ++count;
                }
            }
        }
        out.println(count);
    }
}
