// Super Reduced String
// https://www.hackerrank.com/challenges/reduced-string/problem
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

public class SuperReducedString {

    // Complete the superReducedString function below.
    static String superReducedString(String s) {
        String result = "";
        for(int i = 0; i < s.length(); i++){
            if(result.length() == 0){
                result += s.charAt(i);
                continue;
            }
            if(result.charAt(result.length()-1) == s.charAt(i)){
                result = result.substring(0, result.length()-1);
                continue;
            }
            result += s.charAt(i);
        }
        if(result.length() == 0){
            return "Empty String";
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        String result = superReducedString(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}