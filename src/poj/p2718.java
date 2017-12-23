package poj;

import java.util.Arrays;
import java.util.Scanner;

public class p2718 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();
        for (int i = 0; i < n; i++) {
            String str = in.nextLine();
            str = str.replaceAll("\\s+", "");
            str = sort(str);
            int diff = solve(str);
            System.out.println(diff);
        }
        in.close();
    }
    private static int solve(String str) {
        int length = str.length();
        int diff = 10000000;
        String small, bigger;
        int str_diff;
        if (length % 2 == 0) {
            for (int i = 0; i < length - 1; i++) {
                if (str.charAt(i) == '0') {
                    if (length == 2) return str.charAt(1) - str.charAt(0);
                    continue;
                }
                if (length - i - 2 >= length / 2 - 1) {
                    small = str.charAt(i) + reverse(str.substring(length / 2 + 1, length));
                    bigger = str.charAt(i+1) + str.substring(0, i) + str.substring(i+2, length/2 + 1);
                    str_diff = Math.abs(Integer.parseInt(bigger) - Integer.parseInt(small));
                    if (str_diff < diff) {diff = str_diff;}
                }
                else {
                    small = str.charAt(i) + reverse(str.substring(length / 2 - 1, i) + str.substring(i+2, length));
                    bigger = str.charAt(i+1) + str.substring(0, length / 2 - 1);
                    str_diff = Math.abs(Integer.parseInt(bigger) - Integer.parseInt(small));
                    if (str_diff < diff) {diff = str_diff;}
                }
            }
        }
        else {
            if (str.charAt(0) == '0') {
                small = str.charAt(1) + "" + str.charAt(0);
                if (length > 3) small += str.substring(2, length / 2 + 1);
                bigger = reverse(str.substring(length / 2 + 1, length));
                str_diff = Math.abs(Integer.parseInt(bigger) - Integer.parseInt(small));
                if (str_diff < diff) {diff = str_diff;}
            }
            else {
                small = str.substring(0, length / 2 + 1);
                bigger = reverse(str.substring(length / 2 + 1, length));
                str_diff = Math.abs(Integer.parseInt(bigger) - Integer.parseInt(small));
                if (str_diff < diff) {diff = str_diff;}
            }
        }
        return diff;
    }
    private static String reverse(String input) {
        char[] in = input.toCharArray();
        int begin = 0;
        int end = in.length - 1;
        char temp;
        while (end > begin) {
            temp = in[begin];
            in[begin] = in[end];
            in[end] = temp;
            end--;
            begin++;
        }
        return new String(in);
    }
    private static String sort(String str) {
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}
