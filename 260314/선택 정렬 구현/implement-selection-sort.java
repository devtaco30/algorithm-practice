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

           

            for(int i = 0; i < n; i++) {
                int min = i; // 제일 작은 index
                for(int j = i+1; j<n-1; j++){
                    if ( arr[min] > arr[j]) { // 이 인덱스의 값보다 작은걸 찾으면 min 값을 교체
                        min = j;
                    }
                }
                // 교체된 min index 의 값과 i 의 위치의 값을 교환
                int tmp = arr[i];
                arr[i] = arr[min];
                arr[min] = tmp;
            }
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < n; i++){
                sb.append(arr[i] + " ");
            }

            System.out.println(sb.toString());
 
            

            
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}