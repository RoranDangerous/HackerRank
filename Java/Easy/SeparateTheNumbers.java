// Separate The Numbers
// https://www.hackerrank.com/challenges/separate-the-numbers/problem
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class SeparateTheNumbers {

    // Complete the separateNumbers function below.
    static void separateNumbers(String s) {
        long last = 0;
        if(s.charAt(0) == '0' || s.length() == 1){
            System.out.println("NO");
            return;
        }
        
        for(int i = 0; i < s.length(); i++){
            if(last == 0){
                last = (int)s.charAt(i) - 48;
                continue;
            }
            
            
            if(isSatisfying(last, s, i)){
                System.out.println("YES "+last);
                return;
            }
            else{
                last *= 10;
                last += ((int)s.charAt(i) - 48);
            }
        }
        System.out.println("NO");
    }
    
    static boolean isSatisfying(long last, String s, int index){
        String lastS = (last + 1) + "";
        
        if(index >= s.length()){
            return true;
        }
        
        if(index+lastS.length() > s.length()){
            return false;
        }
        
        if(!s.substring(index, index+lastS.length()).equals(lastS)){
            return false;
        }
        
        return isSatisfying(last+1, s, index+lastS.length());
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s = scanner.nextLine();

            separateNumbers(s);
        }

        scanner.close();
    }
}
