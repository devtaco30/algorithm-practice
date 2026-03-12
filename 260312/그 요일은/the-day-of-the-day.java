import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m1 = sc.nextInt();
        int d1 = sc.nextInt();
        int m2 = sc.nextInt();
        int d2 = sc.nextInt();
        String A = sc.next();
        // Please write your code here.
        List<String> days = new ArrayList<>(Arrays.asList("Sun", "Mon", "Tue","Wed", "Thu", "Fri", "Sat"));
        int[] num_of_days = {0,31,29,31,30,31,30,31,31,30,31,30,31};

        int start = 0;
        for (int i = 0; i < m1; i++){
            start +=num_of_days[i];
        }
        start +=d1;

        int end = 0;
        for (int i = 0; i < m2; i++){
            end +=num_of_days[i];
        }
        end +=d2;

        int period = end - start; // 기간

        int cnt = period / 7; // 완전한 주 수 = 최소 A 요일 등장 횟수
        int remain = period % 7; // 나머지 일수 (0~6). 기간 내 마지막 (remain+1)일이 불완전한 주
        // 그 구간의 요일: (1+0)%7, (1+1)%7, ..., (1+remain)%7. A(idx)가 여기 포함되면 +1
        int idx = days.indexOf(A);
        int firstOffsetOfA = (idx - 1 + 7) % 7; // 시작(월요일)로부터 A가 처음 나오는 날의 오프셋
        if (firstOffsetOfA <= remain) {
            cnt++;
        } 

        System.out.println(cnt);
    }
}