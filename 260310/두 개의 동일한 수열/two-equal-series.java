import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            b[i] = sc.nextInt();
        }
        // Please write your code here.

        Arrays.sort(a);
        Arrays.sort(b);

        for (int i = 0; i<n; i++){
            if (a[i] != b[i]) {
                System.out.println("No");
                return;                
            }
        }

        System.out.println("Yes");
    }
}