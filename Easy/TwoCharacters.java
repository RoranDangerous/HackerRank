// Two Characters
// https://www.hackerrank.com/challenges/two-characters/problem
// P.S. Not as easy
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class TwoCharacters {

    // Complete the alternate function below.
    static int alternate(String s) {
        Letter[] original = new Letter[26];
        int different_letters = 0;
        for(int i = 0; i < s.length(); i++){
            char letter = s.charAt(i);
            int numeric = (int)letter - 97;
            for(int j = 0; j < original.length; j++){
                if(original[j] == null || numeric == j){
                    continue;
                }
                original[j].last_relation |= (1 << numeric);
            }
            if(original[numeric] == null){
                different_letters++;
                original[numeric] = new Letter();
                continue;
            }
            original[numeric].count++;
            original[numeric].relation &= original[numeric].last_relation;
            original[numeric].last_relation = 0;
        }
        
        int max = 0;
        if(different_letters < 2){
            return 0;
        }
        for(int i = 0; i < original.length; i++){
            if(original[i] == null){
                continue;
            }
            for(int j = 0; j < original.length; j++){
                if(((original[i].relation >> j) & 1) == 0){
                    continue;
                }
                if(original[j] == null || ((original[j].relation >> i) & 1) == 0){
                    continue;
                }
                int current_max = original[i].count + original[j].count;
                max = current_max > max ? current_max : max;
            }
        }
        return max;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int l = Integer.parseInt(bufferedReader.readLine().trim());

        String s = bufferedReader.readLine();

        int result = alternate(s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
    
    private static class Letter{
        public int count = 1;
        public long last_relation = 0;
        public long relation = (1 << 27) - 1;
    }
}
