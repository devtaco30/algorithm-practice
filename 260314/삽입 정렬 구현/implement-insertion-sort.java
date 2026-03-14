import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = Integer.parseInt(br.readLine());

            int[] arr = new int[n];

            StringTokenizer st = new StringTokenizer(br.readLine());
            
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            // 정렬

            for (int i = 1; i < n ; i++){
                int j = i - 1;
                int k = arr[i]; // key

                while ( j >= 0 && arr[j] > k ) {
                    arr[j+1] = arr[j];
                    j--;
                    
                }
                arr[j+1] = k;
            }

            StringBuilder sb = new StringBuilder();
            for ( int i = 0; i<n; i++){
                sb.append(arr[i] + " ");
            }

            System.out.println(sb.toString());


        } catch(IOException e){
            e.printStackTrace();
        }
    }
}