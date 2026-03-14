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

                for (int i = 0; i<n; i++){
                    for(int j = 0; j < n-1; j++){
                        if ( arr[j] > arr[j+1]) {
                            int tmp = arr[j];
                            arr[j] = arr[j+1];
                            arr[j+1] = tmp;
                        }
                    }
                }

                StringBuilder sb = new StringBuilder();
                for(int i = 0; i < n; i++){
                    sb.append(arr[i]+" ");
                }

                System.out.println(sb.toString());



        } catch (IOException e){
            e.printStackTrace();
        }
    }
}