package poj;

import java.util.Scanner;

public class p3187 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int sum = in.nextInt();
        int[] combs = solve(N, sum);
        for (int i = 0; i < N; i++) {
            System.out.print(combs[i] + " ");
        }
        System.out.println();
        in.close();
    }

    /**
     * Compute the factorial of n
     * @param n the number n
     * @return the factorial of n
     */
    private static int facts(int n) {
        if (n < 2) return 1;
        return n * facts(n-1);
    }

    /**
     * Compute the combanation of mCn
     * @param m the choose number
     * @param n the total number
     * @return the ways of choose
     */
    private static int combs(int m, int n) {
        return facts(n) / facts(m) / facts(n - m);
    }
    private static int[] solve(int N, int sum) {
        int[] arrs = new int[N];
        for (int i = 1; i <= N; i++) {
            arrs[i-1] = i;
        }
        if (permSum(arrs, N) == sum) return arrs;
        int[] perms;
        while ((perms = nextPermutation(arrs)) != null) {
            int permSum = permSum(perms, N);
            if (permSum == sum) return perms;
        }
        return null;
    }
    private static int permSum(int[] perms, int n) {
        int permSum = 0;
        for (int i = 0; i < n; i++) {
            permSum += combs(i, n - 1) * perms[i];
        }
        return permSum;
    }
    private static int[] nextPermutation( final int[] c ) {
        // 1. finds the largest k, that c[k] < c[k+1]
        int first = getFirst( c );
        if ( first == -1 ) return null; // no greater permutation
        // 2. find last index toSwap, that c[k] < c[toSwap]
        int toSwap = c.length - 1;
        while ( c[ first ] >= c[ toSwap ])
            --toSwap;
        // 3. swap elements with indexes first and last
        swap( c, first++, toSwap );
        // 4. reverse sequence from k+1 to n (inclusive)
        toSwap = c.length - 1;
        while ( first < toSwap )
            swap( c, first++, toSwap-- );
        return c;
    }

    // finds the largest k, that c[k] < c[k+1]
    // if no such k exists (there is not greater permutation), return -1
    private static int getFirst( final int[] c ) {
        for ( int i = c.length - 2; i >= 0; --i )
            if ( c[ i ] < c[ i + 1 ])
                return i;
        return -1;
    }

    // swaps two elements (with indexes i and j) in array
    private static void swap( final int[] c, final int i, final int j ) {
        final int tmp = c[ i ];
        c[ i ] = c[ j ];
        c[ j ] = tmp;
    }
}
