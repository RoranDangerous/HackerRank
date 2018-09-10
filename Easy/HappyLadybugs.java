// Happy Ladybugs
// https://www.hackerrank.com/challenges/happy-ladybugs/problem
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class HappyLadybugs {

    // Complete the happyLadybugs function below.
    static String happyLadybugs(String b) {
        String result = "YES";
        int[] chars = new int[27];
        char last = '0';
        boolean verification_required = false;
        for(int i = 0; i < b.length(); i++){
            if(b.charAt(i) == '_'){
                chars[26]++;
                continue;
            }
            char current = b.charAt(i);
            if(verification_required){
                chars[current-65]++;
                continue;
            }
            if(last != current){
                if(result.equals("YES")){
                    result = "NO";
                }
                else{
                    verification_required = true;
                }
            }
            else{
                result = "YES";
            }
            last = current;
            chars[current-65]++;
        }
        
        if(!verification_required){
            return result;
        }
        else{
            for(int i = 0; i < chars.length-1; i++){
                if(chars[i] == 1){
                    return "NO";
                }
            }
            if(chars[26] > 0){
                return "YES";
            }
            return "NO";
        }
        
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int g = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int gItr = 0; gItr < g; gItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            String b = scanner.nextLine();

            String result = happyLadybugs(b);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}