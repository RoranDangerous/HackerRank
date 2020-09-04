// Sherlock and Array
// https://www.hackerrank.com/challenges/sherlock-and-array/problem
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

public class SherlockAndArray {

    // Complete the balancedSums function below.
    static String balancedSums(List<Integer> arr) {
        int i = 0;
        int j = arr.size()-1;
        int left = arr.get(i);
        int right = arr.get(j);
        while(i != j){
            if(i > j){
                return "NO";
            }
            if(left > right){
                j--;
                right += arr.get(j);
                continue;
            }
            if(left < right){
                i++;
                left += arr.get(i);
                continue;
            }
            i++;
            left += arr.get(i);
            j--;
            right += arr.get(j);
        }
        return left == right ? "YES" : "NO";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int T = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, T).forEach(TItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());

                String result = balancedSums(arr);

                bufferedWriter.write(result);
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}