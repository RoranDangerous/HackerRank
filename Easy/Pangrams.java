// Pangrams
// https://www.hackerrank.com/challenges/pangrams/problem
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Pangrams {

    // Complete the pangrams function below.
    static String pangrams(String s) {
        s = s.toUpperCase();
        int count = 26;
        boolean[] dict = new boolean[26];
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == ' '){
                continue;
            }
            if(dict[(int)s.charAt(i)-65]){
                continue;
            }
            dict[(int)s.charAt(i)-65] = true;
            count--;
            if(count == 0){
                return "pangram";
            }
        }
        return "not pangram";

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scanner.nextLine();

        String result = pangrams(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}