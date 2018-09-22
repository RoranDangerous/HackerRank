// The Full Counting Sort
// https://www.hackerrank.com/challenges/countingsort4/problem
// 1. 1 test case is terminated due to timeout
// 2. All passed
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

public class TheFullCountingSort {

    public static BufferedWriter bufferedWriter;
    
    public static void addToNode(Node root, int index, String word){
        if(root.index == index){
            root.word.append(word+" ");
            return;
        }
        
        if(root.index > index){
            if(root.smaller == null){
                root.smaller = new Node(index, word);
                return;
            }
            addToNode(root.smaller, index, word);
            return;
        }
        
        if(root.bigger == null){
            root.bigger = new Node(index, word);
            return;
        }
        
        addToNode(root.bigger, index, word);
        return;
    }
    
    static void printResult(Node root) throws IOException{
        if(root.smaller != null){
            printResult(root.smaller);
        }
        bufferedWriter.write(String.valueOf(root.word));
        if(root.bigger != null){
            printResult(root.bigger);
        }
    }
    
    private static class Node{
        public int index;
        public Node bigger = null;
        public Node smaller = null;
        public StringBuilder word = new StringBuilder();
        
        public Node(int index, String word){
            this.index = index;
            this.word.append(word+" ");
        }
    }

    public static void main(String[] args) throws IOException {
        
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<String>> arr = new ArrayList<>();

        Node root = new Node(-1, "-");

        IntStream.range(0, n).forEach(i -> {
            try {
                final String[] pair = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
                if(root.index == -1){
                    root.index = Integer.parseInt(pair[0]);
                }
                else{
                    addToNode(root, Integer.parseInt(pair[0]), i < n/2 ? "-" : pair[1]);
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        
        
        printResult(root);

        bufferedReader.close();
        bufferedWriter.close(); 
    }
}
