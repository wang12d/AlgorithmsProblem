package poj;

import java.io.PrintStream;
import java.util.*;


public class p2010
{
    private static class Cow implements Comparable<Cow>
    {
        private int score;
        private int aid;
        private static final Comparator SCORE_COMPARATOR = new scoreComparator();

        private Cow(int score, int aid)
        { this.score = score; this.aid = aid; }

        public int compareTo(Cow b)
        {
            if (this.aid > b.aid) return -1;
            if (this.aid < b.aid) return +1;
            return 0;
        }
        private static class scoreComparator implements Comparator<Cow>
        {
            public int compare(Cow a, Cow b)
            {
                if (a.score > b.score) return +1;
                if (a.score < b.score) return -1;
                return 0;
            }
        }
    }

    public static void main(String[] args)
    {
        PrintStream out = new PrintStream(System.out);
        Scanner in = new Scanner(System.in);

        int N = in.nextInt();
        int C = in.nextInt();
        int F = in.nextInt();

        Cow[] cows = new Cow[C];
        for (int i = 0; i < C; i++)
            cows[i] = new Cow(in.nextInt(), in.nextInt());

        Arrays.sort(cows, Cow.SCORE_COMPARATOR);
        int[] low = new int[C];
        int[] high = new int[C];

        {
            int total = 0;
            PriorityQueue<Cow> q = new PriorityQueue<>();
            for (int i = 0; i < C; i++)
            {
                low[i] = q.size() == N / 2 ? total : 0x3f3f3f3f;
                total += cows[i].aid;
                q.add(cows[i]);
                if (q.size() > N / 2)
                {
                    total -= q.peek().aid;
                    q.poll();
                }
            }
        }

        {
            int total = 0;
            PriorityQueue<Cow> q = new PriorityQueue<>();
            for (int i = C - 1; i >= 0; i--)
            {
                high[i] = q.size() == N / 2 ? total : 0x3f3f3f3f;
                total += cows[i].aid;
                q.add(cows[i]);
                if (q.size() > N / 2)
                {
                    total -= q.peek().aid;
                    q.poll();
                }
            }
        }

        int result = -1;
        for (int i = C - 1; i >= 0; i--)
        {
            if (low[i] + cows[i].aid + high[i] <= F)
            {
                result = cows[i].score;
                break;
            }
        }

        out.println(result);

    }
}
