import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[2 * n];
        for (int i = 0; i < 2 * n; i++) {
            nums[i] = sc.nextInt();
        }
        // Please write your code here.
        // 2N 개의 숫자. 2개씩 묶어서 그룹. 그룹 원소의 합의 '최대값'이 최소가 되는 케이스를 구함
        // 최대끼리 만나게 하는것보다 최대+최소를 만족 시키면 되지 않을까
        Arrays.sort(nums);

        int max = 0;

        for (int i = 0; i <n; i++){

            int currSum = nums[i] + nums[2*n-i-1];

            if ( currSum > max ) {
                max = currSum;
            }

        }

        System.out.println(max);

    }
}