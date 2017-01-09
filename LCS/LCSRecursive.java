import java.util.*;
import java.io.*;

public class LCSRecursive {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("in.txt"));
        String s1 = sc.next();
        String s2 = sc.next();

        String lcs = lcs(s1, s2);
        System.out.println(lcs);
    }

    public static String lcs(String a, String b) {
        int aLen = a.length();
        int bLen = b.length();
        if (aLen == 0 || bLen == 0) {
            return "";
        } else if (a.charAt(aLen - 1) == b.charAt(bLen - 1)) {
            return lcs(a.substring(0, aLen - 1), b.substring(0, bLen - 1)) + a.charAt(aLen - 1);
        } else {
            String x = lcs(a, b.substring(0, bLen - 1));
            String y = lcs(a.substring(0, aLen - 1), b);
            return (x.length() > y.length()) ? x : y;
        }
    }
}