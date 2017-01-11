import java.util.*;
import java.io.*;

public class KMP {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("in.txt"));
        String str = sc.next();
        String pattern = sc.next();

        KMPSearch(str, pattern);
    }

    public static void KMPSearch(String str, String pat) {
        int M = pat.length();
        int N = str.length();

        int [] lps = computeLPSArray(pat);

        int j = 0;
        int i = 0;
        while (i < N){
            if(pat.charAt(j)==str.charAt(i)){
                i++;
                j++;
            }
            if(j==M){
                System.out.println("Pattern Found at index "+(i-j));
                j = lps[j-1];
            }
            else if(i<N && pat.charAt(j)!=str.charAt(i)){
                if(j != 0){
                    j = lps[j-1];
                } else {
                    i++;
                }
            }
        }
    }
    public static int[] computeLPSArray(String pat){
        int M = pat.length();
        int lpsLen = 0;
        int i = 1;
        int[] lps = new int[M];
        lps[0] = 0;
        while(i<M) {
            if(pat.charAt(i) == pat.charAt(lpsLen)){
                lpsLen++;
                lps[i] = lpsLen;
                i++;
            } else {
                if(lpsLen != 0){
                    lpsLen = lps[lpsLen - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }
}