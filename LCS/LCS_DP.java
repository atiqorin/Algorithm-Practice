import java.util.*;
import java.io.*;

public class LCS_DP {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("in.txt"));
        String s1 = sc.next();
        String s2 = sc.next();

        String lcs = lcs(s1, s2);
        System.out.println(lcs);
    }

    public static String lcs(String a, String b) {
        int [][] LCS = new int[a.length()+1][b.length()+1];

        for(int i=0; i<a.length();i++){
            for(int j=0; j<b.length(); j++){
                if(a.charAt(i) == b.charAt(j)){
                    LCS[i+1][j+1] = LCS[i][j] + 1;
                } else {
                    LCS[i+1][j+1] = Math.max(LCS[i+1][j], LCS[i][j+1]);
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();

        for(int i=a.length(), j=b.length(); i!=0 && j!=0;){
            if(a.charAt(i-1) == b.charAt(j-1)){
                sb.append(a.charAt(i-1));
                i--;
                j--;
            } else if(LCS[i][j] == LCS[i-1][j]){
                i--;
            } else if(LCS[i][j] == LCS[i][j-1]){
                j--;
            }
        }

        return sb.reverse().toString();
    }
}