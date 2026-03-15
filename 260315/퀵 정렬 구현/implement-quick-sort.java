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

            QuickSort sort = new QuickSort();

            sort.sort(arr);

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n ; i++){
                sb.append(arr[i] + " ");
            }
            System.out.println(sb.toString());
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}

class QuickSort {

    public void sort(int[] arr) {
        // arr, low, high
        quickSort(arr, 0, arr.length-1);
    }

    private static void quickSort(int[] arr, int low, int high) {
        if ( low < high ) { // 같은거면 끝
            int pivot = partition(arr, low, high);
            quickSort(arr, low, pivot - 1); // pivot 을 제외한 low ~ pivot - 1
            quickSort(arr, pivot+1, high); // pivot 을 제외한 pivot + 1 ~ high
        }
    }

    private static int partition(int[] arr, int low, int high){
        int pivot = arr[high]; // 우선 맨 뒤를 대상으로 선정
        int i = low - 1; // -1이 되도 된다.

        for(int j = low; j < high; j++) { // low ~ high 까지 
            if (arr[j] <= pivot) { // 현재 값이 pivot 보다 작으면
                i++; // pointer 를 한칸 옮기고, 
                swap(arr, i, j); //  
            }
        }

        swap(arr, i+1, high); // pivot 과 i 마지막 + 1 위치를 swap
        return i + 1; 
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}