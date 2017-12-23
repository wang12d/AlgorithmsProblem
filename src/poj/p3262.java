package poj;

import java.util.*;

public class p3262
{
    private static class Cow implements Comparable<Cow>
    {
        private final int D;
        private final int T;
        public Cow(int T, int D)
        {
            this.D = D; this.T = T;
        }
        public int compareTo(Cow that)
        {
            if (this.D * that.T < this.T * that.D) return +1;
            else if (this.D * that.T > this.T * that.D) return -1;
            return 0;
        }
    }
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int allD = 0;   // 总的毁坏
        Cow[] cows = new Cow[N];
        for (int i = 0; i < N; i++)
        {
            cows[i] = new Cow(in.nextInt(), in.nextInt());
            allD += cows[i].D;
        }
        Arrays.sort(cows);
        long answer = 0;
        for (int i = 0; i < N; i++)
        {
            allD -= cows[i].D;
            answer += allD * 2 * cows[i].T;
        }
        System.out.println(answer);
    }
}
