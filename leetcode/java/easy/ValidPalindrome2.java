
/**
 * 문자열 s가 주어졌을때, 최대 하나의 문자를 지워서 palindrome(회문, 거꾸로 읽어도 똑같은 문자열 - ex: 이하이)이
 * 가능하면 true를 리턴하는 함수를 작성하세요. (지우지 않고 palindrome이 되어도 true를 리턴)
 */
public class ValidPalindrome2 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println("정답 = true 현재 풀이 값 =" + solution.isPalindrome("aulcuuculua"));
        System.out.println("정답 = true 현재 풀이 값 =" + solution.isPalindrome("eccer"));;
        System.out.println("정답 = true 현재 풀이 값 =" + solution.isPalindrome("adpeolloepa"));

    }
}

class Solution {
    public boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                // 왼쪽 건너뛰기 또는 오른쪽 건너뛰기 시도
                return trySkip(s, left + 1, right) || trySkip(s, left, right - 1);
            }
            left++;
            right--;
        }
        return true;
    }
    
    private boolean trySkip(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }
}