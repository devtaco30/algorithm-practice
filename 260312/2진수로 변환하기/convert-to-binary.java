import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        int[] binary = new int[20];
        int idx = 0;

        while (true) {
            if (n<2){
                binary[idx++] = n;
                break;
            }

            binary[idx++] = n%2;
            n /= 2;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = idx-1; i>=0; i--){
            sb.append(binary[i]);
        }

        System.out.print(sb.toString());



    }
}