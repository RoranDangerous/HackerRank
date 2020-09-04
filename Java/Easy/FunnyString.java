// Funny String
// https://www.hackerrank.com/challenges/funny-string/problem
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class FunnyString {

    // Complete the funnyString function below.
    static String funnyString(String s) {
        int lastL, lastR;
        lastL = lastR = -1;
        for(int i = 0 ;i <= s.length()/2; i++){
            if(lastL == -1){
                lastL = (int)s.charAt(i);
                lastR = (int)s.charAt(s.length() - 1 - i);
                continue;
            }
            int lDif = Math.abs((int)s.charAt(i) - lastL);
            int rDif = Math.abs((int)s.charAt(s.length() - 1 - i) - lastR);
            if(lDif != rDif){
                return "Not Funny";
            }
            lastL = (int)s.charAt(i);
            lastR = (int)s.charAt(s.length() - 1 - i);
        }
        return "Funny";
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s = scanner.nextLine();

            String result = funnyString(s);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}