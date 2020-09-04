// Insertion Sort - Part 1
// https://www.hackerrank.com/challenges/insertionsort1/problem
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class InsertionSortP1 {

    // Complete the insertionSort1 function below.
    static void insertionSort1(int n, int[] arr) {
        boolean isInserted=false;
        int unsortedval=-1;
        int unsortedindex=-1;
        for(int i=arr.length-1;i>0;i--){
        
            if(arr[i]<arr[i-1]){ 
                unsortedval=arr[i];
                unsortedindex=i;
                break;
            }
            
        }
        
        for(int i=unsortedindex;i>=0;i--){
         
            if(i>0 && arr[i-1]>=unsortedval){ 
             arr[i]=arr[i-1];
            }else{
             arr[i]=unsortedval;
            isInserted=true;
            }
            
            for(int j=0;j<arr.length;j++){
                System.out.print(arr[j]+" ");
            }
            
            System.out.print("\n");
            
            if(isInserted)
                break;
        }

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        insertionSort1(n, arr);

        scanner.close();
    }
}