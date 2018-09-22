// Big Sorting
// https://www.hackerrank.com/challenges/big-sorting/problem
// 1. Didn't pass 2 test cases due to timeout. Not giving up, still thinking :)
// 2. Newer version has only 1 test case terminated due to timeout.
// 3. Passed all test cases
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class BigSorting {
    
    static void addToL(LNode root, String el){            
        if(root.value == el.length()){
            addToN(root.number, el);
            return;
        }
        
        if(root.value > el.length()){
            if(root.smaller == null){
                root.smaller = new LNode(el);
                return;
            }
            addToL(root.smaller, el);
            return;
        }
        
        if(root.bigger == null){
            root.bigger = new LNode(el);
            return;
        }
        addToL(root.bigger, el);
    }
    
    static void addToN(NNode root, String el){
        if(root.value.equals(el)){
            root.count++;
            return;
        }
        
        if(isBigger(root.value, el)){
            if(root.smaller == null){
                root.smaller = new NNode(el);
                return;
            }
            addToN(root.smaller, el);
            return;
        }
        
        if(root.bigger == null){
            root.bigger =  new NNode(el);
            return;
        }
        addToN(root.bigger, el);
    }
    
    static boolean isBigger(String one, String two){
        if(one.length() != two.length()){
            return one.length() > two.length();
        }
        
        return one.compareTo(two) > 0;
    }
    
    static void toResult(LNode root) throws IOException {
        if(root.smaller != null){
            toResult(root.smaller);
        }
        nToResult(root.number);
        if(root.bigger != null){
            toResult(root.bigger);
        }
    }
    
    static void nToResult(NNode root) throws IOException {
        if(root.smaller != null){
            nToResult(root.smaller);
        }
        for(int i = 0; i < root.count; i++){
            bufferedWriter.write(root.value);
            bufferedWriter.newLine();
        }
        if(root.bigger != null){
            nToResult(root.bigger);
        }
    }
    
    private static class LNode{
        public int value = 0;
        public LNode bigger = null;
        public LNode smaller = null;
        public NNode number = null;
        
        public LNode(String el){
            value = el.length();
            number = new NNode(el);
        } 
    }
    
    private static class NNode{
        int count = 0;
        public NNode bigger = null;
        public NNode smaller = null;
        public String value = "";
        
        public NNode(String v){
            value = v;
            count = 1;
        }
    }

    private static final Scanner scanner = new Scanner(System.in);
    public static BufferedWriter bufferedWriter;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());
        
        LNode root = null;

        for (int i = 0; i < n; i++) {
            String unsortedItem = bufferedReader.readLine().replaceAll("\\s+$", "");
            if(root == null){
                root = new LNode(unsortedItem);
                continue;
            }
            addToL(root, unsortedItem);
        }
        
        toResult(root);

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
