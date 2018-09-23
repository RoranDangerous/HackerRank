// Fraudulent Activity Notification
// https://www.hackerrank.com/challenges/fraudulent-activity-notifications/problem
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class FraudulentActivityNotification {

    // Complete the activityNotifications function below.
    static int activityNotifications(int[] expenditure, int d) {
        int result = 0;
        boolean two = (d % 2 == 0);
        Node root = new Node(expenditure[0]);
        for(int i = 1; i < expenditure.length; i++){
            if(i < d){
                add(root, expenditure[i]);
                continue;
            }
            
            float median = getMedian(root, d/2, two);
            if(median * 2.0 <= expenditure[i]){
                result++;
            }
            
            remove(root, expenditure[i-d]);
            add(root, expenditure[i]);
        }
        return result;
    }
    
    public static void remove(Node root, int value){
        if(root.value == value){
            root.count--;
            return;
        }
        
        if(root.value > value){
            root.countLeft--;
            remove(root.smaller, value);
            return;
        }
        
        root.countRight--;
        remove(root.bigger, value);
    }
    
    public static void add(Node root, int value){
        if(root.value == value){
            root.count++;
            return;
        }
        
        if(root.value > value){
            root.countLeft++;
            if(root.smaller == null){
                root.smaller = new Node(value);
                return;
            }
            
            add(root.smaller, value);
            return;
        }
        
        root.countRight++;
        if(root.bigger == null){
            root.bigger = new Node(value);
            return;
        }
        add(root.bigger, value);
    }
    
    public static float getMedian(Node root, int left, boolean two){
        if(left - root.countLeft < 0){
            return getMedian(root.smaller, left, two);
        }
        
        if(left - root.countLeft == 0){
            if(two){
                return (getHighest(root.smaller) + root.value) / 2;
            }
            return getHighest(root.smaller);
        }
        
        left -= (root.countLeft + root.count);
        
        if(left < 0){
            return (float)root.value;
        }
        
        if(left == 0){
            if(two){
                return (getSmallest(root.bigger) + root.value) / 2;
            }
            return getSmallest(root.bigger);
        }
        
        return getMedian(root.bigger, left, two);
    }
    
    static float getHighest(Node root){
        if(root.bigger != null){
            return getHighest(root.bigger);
        }
        
        return (float)root.value;
    }
    
    static float getSmallest(Node root){
        if(root.smaller != null){
            return getSmallest(root.smaller);
        }
        
        return (float)root.value;
    }
    
    public static class Node{
        public int value = -1;
        public int count = 0;
        public Node bigger = null;
        public Node smaller = null;
        public int countLeft = 0;
        public int countRight = 0;
        
        public Node(int value){
            count = 1;
            this.value = value;
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nd = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nd[0]);

        int d = Integer.parseInt(nd[1]);

        int[] expenditure = new int[n];

        String[] expenditureItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int expenditureItem = Integer.parseInt(expenditureItems[i]);
            expenditure[i] = expenditureItem;
        }

        int result = activityNotifications(expenditure, d);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}