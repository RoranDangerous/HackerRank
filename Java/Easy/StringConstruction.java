// String Construction
// https://www.hackerrank.com/challenges/string-construction/problem
import java.io.;
import java.math.;
import java.security.;
import java.text.;
import java.util.;
import java.util.concurrent.;
import java.util.regex.;

public class StringConstruction {

     Complete the stringConstruction function below.
    static int stringConstruction(String s){
        boolean[] chars = new boolean[26];
        for(int i = 0; i  s.length(); i++){
            chars[(int)s.charAt(i) - 97] = true;
        }
        int count = 0;
        for(int i = 0; i  chars.length; i++){
            if(chars[i]){
                count++;
            }
        }
        return count;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv(OUTPUT_PATH)));

        int q = scanner.nextInt();
        scanner.skip((rn[nru2028u2029u0085]));

        for (int qItr = 0; qItr  q; qItr++) {
            String s = scanner.nextLine();

            int result = stringConstruction(s);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
