// Big Sorting
// https://www.hackerrank.com/challenges/big-sorting/problem
// Didn't pass 2 test cases due to timeout. Not giving up, still thinking :)
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class BigSorting {

    // Complete the bigSorting function below.
    static String[] bigSorting(String[] unsorted) {
        String[] result = new String[unsorted.length];
        for(int i = 0; i < unsorted.length; i++){
            if(result[0] == null){
                result[0] = unsorted[i];
                continue;
            }
            int j = i-1;
            while(j >= 0 && isBigger(result[j], unsorted[i])){
                result[j+1] = result[j];
                j--;
            }
            result[j+1] = unsorted[i];
        }
        return result;
    }
    
    static boolean isBigger(String one, String two){
        if(one.length() != two.length()){
            return one.length() > two.length();
        }
        
        return one.compareTo(two) > 0;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String[] unsorted = new String[n];

        for (int i = 0; i < n; i++) {
            String unsortedItem = scanner.nextLine();
            unsorted[i] = unsortedItem;
        }

        String[] result = bigSorting(unsorted);

        for (int i = 0; i < result.length; i++) {
            bufferedWriter.write(result[i]);

            if (i != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
