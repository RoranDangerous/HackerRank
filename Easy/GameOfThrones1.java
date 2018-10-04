// Game Of Thrones I
// https://www.hackerrank.com/challenges/game-of-thrones/problem
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class GameOfThrones1 {

    // Complete the gameOfThrones function below.
    static String gameOfThrones(String s) {
        boolean[] chars = new boolean[26];
        for(int i = 0; i < s.length(); i++){
            int index = (int)s.charAt(i)-97;
            chars[index] = !chars[index];        
        }
        boolean first = false;
        for(int i = 0; i < chars.length; i++){
            if(chars[i] && first){
                return "NO";
            }
            if(chars[i] && !first){
                first = true;
            }
        }
        return "YES";
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scanner.nextLine();

        String result = gameOfThrones(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}