package poj;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Comparator;

public class p3190
{
    private static class Cow
    {
        private int begin;
        private int end;
        private int stall;
        private int id;

        private Cow(int begin, int end, int stall, int id)
        {
            this.begin = begin;
            this.end = end;
            this.stall = stall;
            this.id = id;
        }

        private static class timeOrder implements Comparator<Cow>
        {
            public int compare(Cow a, Cow b)
            {
                return Integer.compare(a.begin, b.begin);
            }
        }

        private static class idOrder implements Comparator<Cow>
        {
            public int compare(Cow a, Cow b)
            {
                return Integer.compare(a.id, b.id);
            }
        }
    }

    private static class Stall implements Comparable<Stall>
    {
        private int end;
        private int id;

        private Stall(int id) {this.id = id;}

        public int compareTo(Stall that)
        {
            return Integer.compare(this.end, that.end);
        }
    }

    public static void main(String[] args)
    {
        PrintStream out = new PrintStream(System.out);
        Scanner in = new Scanner(System.in);

        int N = in.nextInt();
        Cow[] cows = new Cow[N];
        for (int i = 0; i < N; i++)
        {
            cows[i] = new Cow(in.nextInt(), in.nextInt(), 0, i);
        }
        Arrays.sort(cows, new Cow.timeOrder());
        // 用优先队列处理兽栏
        PriorityQueue<Stall> queue = new PriorityQueue<Stall>();

        int id = 0;
        queue.add(new Stall(++id));
        for (int i = 0; i < N; i++)
        {
            Stall min = queue.poll();
            if (min.end < cows[i].begin)
            {   // 如果能够放在同一个兽栏里面
                min.end = cows[i].end;
                cows[i].stall = min.id;
            }
            else
            {   // 如果不能就增加一个新的兽栏
                Stall newStall = new Stall(++id);
                newStall.end = cows[i].end;
                queue.add(newStall);
                cows[i].stall = newStall.id;
            }
            queue.add(min);
        }

        Arrays.sort(cows, new Cow.idOrder());
        out.println(queue.size());
        for (int i = 0; i < N; i++)
        {
            out.println(cows[i].stall);
        }
    }
}
