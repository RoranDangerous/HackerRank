// Beautiful Binary String
// https://www.hackerrank.com/challenges/beautiful-binary-string/problem
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class BeautifulBinaryString {

    // Complete the beautifulBinaryString function below.
    static int beautifulBinaryString(String b) {
        String ugly = "010";
        int pattern = 0;
        int count = 0;
        for(int i = 0; i < b.length(); i++){
            if(b.charAt(i) == ugly.charAt(pattern)){
                pattern++;
            }
            else{
                pattern = 0;
                if(b.charAt(i) == ugly.charAt(0)){
                    pattern++;
                }
            }
            if(pattern == ugly.length()){
                count++;
                pattern = 0;
            }
        }
        return count;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String b = scanner.nextLine();

        int result = beautifulBinaryString(b);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}