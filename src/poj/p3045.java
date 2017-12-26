package poj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p3045
{
    private static class Cow implements Comparable<Cow>
    {
        private final int weight;
        private final int strength;

        private Cow(int weight, int strength)
        {
            this.strength = strength;
            this.weight = weight;
        }

        public int compareTo(Cow that)
        {
            if (strength + weight > that.weight + that.strength)        return +1;
            else if (strength + weight < that.weight + that.strength)   return -1;
            return 0;
        }
    }

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int totalWeight = 0;
        int n = Integer.parseInt(br.readLine());
        Cow[] cows = new Cow[n];
        for (int i = 0; i < n; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int strength = Integer.parseInt(st.nextToken());
            totalWeight += weight;
            cows[i] = new Cow(weight, strength);
        }

        Arrays.sort(cows);

        int risks = Integer.MIN_VALUE;
        for (int i = n-1; i >= 0; i--)
        {
            totalWeight -= cows[i].weight;
            risks = Math.max(risks, totalWeight - cows[i].strength);
        }

        System.out.println(risks);
    }
}
