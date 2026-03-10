import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        for (int i = 0; i < n; i++) {
            Arrays.sort(arr, 0, i+1);
            if ( (i+1)%2 != 0 ) {
                System.out.print(arr[((i+1)/2)] + " ");
            }
            
        }


    }
}