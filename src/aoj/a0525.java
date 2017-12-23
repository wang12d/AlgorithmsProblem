package aoj;

import java.util.BitSet;
import java.util.Scanner;

public class a0525 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (true) {
            int r = in.nextInt(), c = in.nextInt();
            if (r == 0 && c == 0) {
                in.close();
                break;
            }
            BitSet[] bits = new BitSet[r];
            for (int i = 0; i < r; i++) {
                bits[i] = new BitSet(c);
                for (int j = 0; j < c; j++) {
                    int input = in.nextInt();
                    if (input == 1) bits[i].set(j);
                }
            }
            int perms = 1 << r;  // 行数一共有2^r总变换
            int result = 0;
            for (int i = 0; i < perms; i++) {
                // 对行进行变换
                for (int j = 0; j < r; j++) {
                    if ((i & (1 << j)) > 0) {
                        bits[j].flip(0, c);    // 翻转
                    }
                }
                // 对于列数，只要统计其上面和反面的数量，取最大值即可
                int possible = 0;
                for (int k = 0; k < c; k++) {
                    int up = 0;
                    for (int v = 0; v < r; v++) {
                        if (bits[v].get(k)) {
                            up++;
                        }
                    }
                    possible += Math.max(up, r - up);
                }
                result = Math.max(result, possible);
                for (int u = 0; u < r; u++) {
                    if ((i & (1 << u)) > 0) {
                        bits[u].flip(0, c);
                    }
                }
            }
            System.out.println(result);
        }
    }
}
