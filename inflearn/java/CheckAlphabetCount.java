/**
 * Q.
 * 1. 입력으로 소문자의 알파벳 순으로 정렬된 문자열이 입력됩니다.
 * 2. 각 알파벳은 중복이 가능합니다.
 * 3. 중간에 없는 알파벳이 있을 수도 있습니다.
 * 
 * 입,출력 예시와 같이 입력 문자열에 나타나는 각 알파벳의 종류,갯수를 요약하여 나타내시오.
 * 
 * ex)
 * input: abc 	output: a1/b1/c1
 * input: aaabbbc	output: a3/b3/c1
 * input: abbbc	output: a1/b3/c1
 * input: ahhhhz	output: a1/h4/z1
 * input: acccdeee	output: a1/c3/d1/e3
 * 
 */
public class CheckAlphabetCount {
    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println("정답 = a1/b1/c1 현재 풀이 값 =" + solution.checkAlphabetCount("abc"));
        System.out.println("정답 = a3/b3/c1 현재 풀이 값 =" + solution.checkAlphabetCount("aaabbbc"));
        System.out.println("정답 = a1/b3/c1 현재 풀이 값 =" + solution.checkAlphabetCount("abbbc"));
        System.out.println("정답 = a1/h4/z1 현재 풀이 값 =" + solution.checkAlphabetCount("ahhhhz"));
        System.out.println("정답 = a1/c3/d1/e3 현재 풀이 값 =" + solution.checkAlphabetCount("acccdeee"));
    }
}

class Solution {
    public String checkAlphabetCount(String str) {
        StringBuilder result = new StringBuilder();
        char[] arr = str.toCharArray();
        char prev = arr[0];
        int count = 1;
        for (int i = 1; i < arr.length; i++ ) { // O(N)
            char current = arr[i];
            if (prev == current ) {
                count++;
            } else {
                result.append(prev).append(count).append("/");
                prev = current;
                count = 1;
            }
        }
        result.append(prev).append(count);
        return result.toString(); // O(N)
        // 총 시간복잡도: O(N)
    }
}

