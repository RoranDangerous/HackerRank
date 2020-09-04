// Anagram
// https://www.hackerrank.com/challenges/anagram/problem
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Anagram {

    // Complete the anagram function below.
    static int anagram(String s) {
        if(s.length() % 2 == 1){
            return -1;
        }
        
        int[] first = new int[26];
        int[] second = new int[26];
        for(int i = 0; i < s.length() / 2; i++){
            first[(int)s.charAt(i) - 97]++;
        }
        for(int i = s.length() / 2; i < s.length(); i++){
            second[(int)s.charAt(i) - 97]++;
        }
        int count = 0;
        for(int i = 0; i < first.length; i++){
            count += Math.abs(first[i] - second[i]);
        }
        return count/2;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s = scanner.nextLine();

            int result = anagram(s);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}