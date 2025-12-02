import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Q. 정수를 입력 했을 때, 그 정수 이하의 소수를 모두 반환하시오. 
 * 
 * 소수는 자신보다 작은 두 개의 자연수를 곱하여 만들 수 없는 1보다 큰 자연수이다.
 */
public class FindPrimeListUnderNumber {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println("정답 = [2, 3, 5, 7, 11, 13, 17, 19] 현재 풀이 값 = " + solution.findPrimeListUnderNumber(20));
    }
}

class Solution {
    public List<Integer> findPrimeListUnderNumber(int number) {
        // 소수는 자기 자신과 1을 제외한 숫자로 나누어 떨어지지 않는 숫자.

        // 자연수 N 은 N = a * b (a<=b) 로 나타낼 수 있다.
        // N 의 제곱근 이하의 수로 나누어 떨어지지 않으면 소수이다. -> a 가 b 보다 커지는 순간 제곱근 이상이 되고, 그 경우는 체크하지 않아도 된다.(어차피 같다)
        // 따라서, 제곱근 이하의 수로 나누어 떨어지지 않으면 소수이다.

        List<Integer> primeList = new ArrayList<>();

        for (int n = 2; n < number; n++) { // O(N)

            boolean isPrime = true;
            for (int i : primeList) {
                if ( i*i <= n && n % i == 0 ) {
                    isPrime =false;
                    break;
                }                
            }
            if ( isPrime ) {
                primeList.add(n);
            }
        } // O(N^2)    ->  그런데 이래도 O(N^2) , 이게 맞는건가?
        return primeList;
    }

    /**
     * 에라토스테네스의 체 방법으로 소수 찾기
     * 
     * [초등학교 5학년을 위한 설명]
     * 
     * 1. 종이에 2부터 원하는 숫자까지 모두 적어요
     *    예: 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20
     * 
     * 2. 가장 작은 숫자 2를 선택해요 (이건 소수예요!)
     * 
     * 3. 2의 배수들을 모두 지워요 (2를 제외하고)
     *    → 4, 6, 8, 10, 12, 14, 16, 18, 20을 지워요
     *    남은 것: 2, 3, 5, 7, 9, 11, 13, 15, 17, 19
     * 
     * 4. 다음 남은 숫자 중 가장 작은 숫자 3을 선택해요 (이것도 소수예요!)
     * 
     * 5. 3의 배수들을 모두 지워요 (3을 제외하고)
     *    → 9, 15를 지워요 (6, 12, 18은 이미 2의 배수로 지워졌어요)
     *    남은 것: 2, 3, 5, 7, 11, 13, 17, 19
     * 
     * 6. 다음 남은 숫자 중 가장 작은 숫자 5를 선택해요
     * 
     * 7. 5의 배수들을 지워요
     *    → 10, 15, 20은 이미 지워졌고, 5는 소수니까 남겨둬요
     * 
     * 8. 이렇게 계속 반복하면, 남은 숫자들이 모두 소수예요!
     * 
     * [왜 이렇게 하면 되나요?]
     * - 합성수(소수가 아닌 수)는 반드시 어떤 소수의 배수예요
     * - 예: 6 = 2 × 3 (2의 배수이자 3의 배수)
     * - 예: 15 = 3 × 5 (3의 배수이자 5의 배수)
     * - 따라서 작은 소수의 배수들을 지우면, 합성수들이 모두 지워져요!
     * 
     * [왜 i*i부터 시작하나요?]
     * - 예: 3의 배수를 지울 때
     *   - 3 × 1 = 3 (3은 소수니까 남겨둬요)
     *   - 3 × 2 = 6 (이미 2의 배수로 지워졌어요)
     *   - 3 × 3 = 9 (여기서부터 지우기 시작해요!)
     * - i보다 작은 배수들은 이미 더 작은 소수로 지워졌기 때문이에요
     * 
     * [왜 j += i 인가요?]
     * - i의 배수를 모두 찾기 위해서예요
     * - 예: i = 3일 때
     *   - j = 9에서 시작
     *   - j += 3 → j = 12 (3의 배수)
     *   - j += 3 → j = 15 (3의 배수)
     *   - j += 3 → j = 18 (3의 배수)
     *   - 이렇게 3씩 더하면 3의 모든 배수를 찾을 수 있어요!
     */
    public List<Integer> findPrimeListUnderNumberBySieve(int number) {
        // 1단계: 모든 숫자를 소수라고 가정하고 배열을 만들어요
        // true = 소수일 가능성이 있음, false = 소수가 아님
        boolean[] isPrime = new boolean[number];
        Arrays.fill(isPrime, true);
        
        // 0과 1은 소수가 아니에요
        if (number > 0) isPrime[0] = false;
        if (number > 1) isPrime[1] = false;
        
        // 2단계: 2부터 시작해서 각 숫자의 배수를 지워나가요
        // i * i < number까지만 확인하면 돼요 (제곱근까지만)
        for (int i = 2; i * i < number; i++) {
            // 만약 i가 아직 소수라고 표시되어 있다면
            if (isPrime[i]) {
                // i의 배수들을 모두 지워요
                // i * i부터 시작하는 이유: i보다 작은 배수들은 이미 지워졌어요
                // j += i는 i씩 더해서 i의 모든 배수를 찾기 위해서예요
                for (int j = i * i; j < number; j += i) {
                    isPrime[j] = false; // 이 숫자는 소수가 아니에요!
                }
            }
        }
        
        // 3단계: 남은 소수들을 리스트에 모아요
        List<Integer> primeList = new ArrayList<>();
        for (int i = 2; i < number; i++) {
            if (isPrime[i]) {
                primeList.add(i);
            }
        }
        
        return primeList;
    }
}