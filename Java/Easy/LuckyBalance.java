// Lucky Balance
// https://www.hackerrank.com/challenges/luck-balance/problem
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class LuckyBalance {

    // Complete the luckBalance function below.
    static int luckBalance(int k, int[][] contests) {
        int total = 0;
        List<Integer> importantContests = new ArrayList<>();
        for(int i = 0; i < contests.length; i++){
            total += contests[i][0];
            if(contests[i][1] == 1){
                importantContests.add(contests[i][0]);
            }
        }
        Collections.sort(importantContests);
        int luckToFlip = 0;
        int mustWinImprCount = importantContests.size() - k;
        for (int i = 0; i < mustWinImprCount; i++){
            luckToFlip += importantContests.get(i);
        }
        int result = total - 2*luckToFlip;
        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[][] contests = new int[n][2];

        for (int i = 0; i < n; i++) {
            String[] contestsRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 2; j++) {
                int contestsItem = Integer.parseInt(contestsRowItems[j]);
                contests[i][j] = contestsItem;
            }
        }

        int result = luckBalance(k, contests);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
