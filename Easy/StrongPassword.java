// Strong Password
// https://www.hackerrank.com/challenges/strong-password/problem
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class StrongPassword {

    // Complete the minimumNumber function below.
    static int minimumNumber(int n, String password) {
        String special_characters = "!@#$%^&*()-+";
        int required_length = 6;
        boolean upper, lower, number, special;
        upper = lower = number = special = false;
        int count = 4;
        for(int i = 0; i < n && !(upper && lower && number && special); i++){
            char c = password.charAt(i);
            if(!upper && (int)c >= 65 && (int)c <= 90){
                count--;
                upper = true;
                continue;
            }
            if(!lower && (int)c >= 97 && (int)c <= 122){
                count--;
                lower = true;
                continue;
            }
            if(!number && (int)c >= 48 && (int)c <= 57){
                count--;
                number = true;
                continue;
            }
            if(!special && special_characters.indexOf(c) != -1){
                count--;
                special = true;
                continue;
            }
        }
        int result = Math.max((required_length - n), count);
        return result;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String password = scanner.nextLine();

        int answer = minimumNumber(n, password);

        bufferedWriter.write(String.valueOf(answer));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}