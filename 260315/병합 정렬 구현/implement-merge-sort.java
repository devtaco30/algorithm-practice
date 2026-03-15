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

            MergeSort sort = new MergeSort();

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

class MergeSort {

    private static int[] mergedArr;

    public void sort(int[] arr) {

        mergedArr = new int[arr.length];
        mergeSort(arr, 0, arr.length-1);
    }

    private static void mergeSort(int[] arr, int low, int high) {
        if ( low < high ) {
            int mid = (low + high)/2;
            mergeSort(arr, low, mid);
            mergeSort(arr, mid+1, high);
            merge(arr, low, mid, high);
        }
    }

    private static void merge(int[] arr, int low, int mid, int high){
        int i = low; // 왼쪽 그룹 시작점
        int j = mid + 1; // 오른쪽 그룹 시작점
        int k = low; // mergedArr 의 시작점 

        while( i <= mid && j <= high ) {
            if ( arr[i] <= arr[j] ) {
                mergedArr[k++] = arr[i++];
            } else {
                mergedArr[k++] = arr[j++];
            }
        }

        while ( i <= mid ) mergedArr[k++] = arr[i++];
        while ( j <= high) mergedArr[k++] = arr[j++];

        for (int l = low ; l <= high ; l ++) {
            arr[l] = mergedArr[l];
        }

    }
}