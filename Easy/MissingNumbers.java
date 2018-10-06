// Missing Numbers
// https://www.hackerrank.com/challenges/missing-numbers/problem
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class MissingNumbers {

    // Complete the missingNumbers function below.
    static void missingNumbers(int[] arr, int[] brr) throws IOException {
        Node root = new Node(brr[0]);
        for(int i = 1; i < brr.length; i++){
            add(root, brr[i]);
        }
        
        for(int i = 0; i < arr.length; i++){
            update(root, arr[i]);
        }
        
        find(root);
    }
    
    public static void find(Node root) throws IOException {
        if(root.smaller != null){
            find(root.smaller);
        }
        if(root.count > 0)
            bufferedWriter.write(String.valueOf(root.value)+" ");
        if(root.bigger != null){
            find(root.bigger);
        }
    }
    
    public static void update(Node root, int value){
        if(root.value == value){
            root.count--;
            return;
        }
        
        if(root.value > value){
            update(root.smaller, value);
            return;
        }

        update(root.bigger, value);
    }
    
    public static void add(Node root, int value){
        if(root.value == value){
            root.count++;
            return;
        }
        
        if(root.value > value){
            if(root.smaller == null){
                root.smaller = new Node(value);
                return;
            }
            add(root.smaller, value);
            return;
        }
        
        if(root.bigger == null){
            root.bigger = new Node(value);
            return;
        }
        add(root.bigger, value);
    }
    
    public static class Node{
        public int value = 0;
        public int count = 0;
        public Node bigger = null;
        public Node smaller = null;
        
        public Node(int v){
            this.value = v;
            this.count = 1;
        }
    }

    private static final Scanner scanner = new Scanner(System.in);
    public static BufferedWriter bufferedWriter;

    public static void main(String[] args) throws IOException {
        bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int m = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] brr = new int[m];

        String[] brrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < m; i++) {
            int brrItem = Integer.parseInt(brrItems[i]);
            brr[i] = brrItem;
        }
        missingNumbers(arr, brr);

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
