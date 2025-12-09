/**
 * 문제: Longest Common Subsequence (LCS)
 * 
 * 문제 설명:
 * 두 개의 문자열 text1과 text2가 주어졌을 때, 가장 긴 공통 subsequence의 길이를 리턴하세요.
 * 만약 공통 subsequence가 없다면 0을 리턴합니다.
 * 
 * Subsequence 정의:
 * - 원본 문자열에서 순서를 바꾸지 않고 여러 문자를 삭제하여 만들 수 있는 문자열
 * - 예를 들어, "ace"는 "abcde"의 subsequence입니다. (b와 d를 삭제하였고, ace는 원본 문자열에서의 순서를 유지)
 * 
 * 예제 1:
 *   입력: text1 = "abcde", text2 = "ace"
 *   출력: 3
 *   설명: 가장 긴 공통 subsequence는 "ace"이고 길이는 3입니다.
 * 
 * 예제 2:
 *   입력: text1 = "abc", text2 = "abc"
 *   출력: 3
 *   설명: 가장 긴 공통 subsequence는 "abc"이고 길이는 3입니다.
 * 
 * 예제 3:
 *   입력: text1 = "abc", text2 = "def"
 *   출력: 0
 *   설명: 공통 subsequence가 없으므로 0을 리턴합니다.
 * 
 * 제약사항:
 *   1 <= text1.length, text2.length <= 1000
 *   text1과 text2는 영문 소문자로만 구성되어 있습니다.
 */
public class LongestCommonSubsequence {

    public static void main(String[] args) {    
        Solution solution = Solution();
    }


}

class Solution {

    public int longestCommonSubsequence(String text1, String text2) {
        
        int len1 = text1.length();
        int len2 = text2.length();

        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 1 ; i <= len1; i++){
            for(int j = 1; j <= len2; j++){

                if (text1.charAt(i-1) == text2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }

            }
        }

        return dp[len1][len2]; // 가장 긴 공통 subsequence의 길이 배열의 마지막
    }

    /**
     * 최적화된 LCS 알고리즘
     * 
     * 최적화 포인트:
     * 1. 공간 복잡도 최적화: 2D 배열(n*m) → 1D 배열 2개(min(n,m))
     *    - O(n*m) → O(min(n,m)) 공간 복잡도
     *    - 이전 행(prev)과 현재 행(curr)만 저장하여 메모리 사용량 감소
     * 
     * 2. 시간 복잡도 개선:
     *    - 더 짧은 문자열을 text2로 설정하여 반복 횟수 최소화
     *    - charAt() 호출 결과를 변수에 저장하여 반복 호출 방지
     *    - 시간 복잡도는 여전히 O(n*m)이지만 상수 시간이 개선됨
     * 
     * 동작 원리:
     * - 기본 DP 로직은 동일: dp[i][j] = text1[0..i-1]과 text2[0..j-1]의 LCS 길이
     * - 2D 배열 대신 이전 행(prev)과 현재 행(curr)만 유지
     * - 각 행을 처리한 후 prev와 curr를 교체하여 다음 행 계산
     * 
     * 예시 (text1="abc", text2="ac"):
     * 초기: prev = [0,0,0], curr = [0,0,0]
     * 
     * i=1 (text1[0]='a'):
     *   j=1: 'a'=='a' → curr[1] = prev[0]+1 = 1
     *   j=2: 'a'!='c' → curr[2] = max(prev[2], curr[1]) = max(0,1) = 1
     *   prev=[0,0,0], curr=[0,1,1] → 교체 후 prev=[0,1,1], curr=[0,0,0]
     * 
     * i=2 (text1[1]='b'):
     *   j=1: 'b'!='a' → curr[1] = max(prev[1], curr[0]) = max(1,0) = 1
     *   j=2: 'b'!='c' → curr[2] = max(prev[2], curr[1]) = max(1,1) = 1
     *   prev=[0,1,1], curr=[0,1,1] → 교체 후 prev=[0,1,1], curr=[0,0,0]
     * 
     * i=3 (text1[2]='c'):
     *   j=1: 'c'!='a' → curr[1] = max(prev[1], curr[0]) = max(1,0) = 1
     *   j=2: 'c'=='c' → curr[2] = prev[1]+1 = 1+1 = 2
     *   최종: prev[2] = 2 반환
     */
    public int longestCommonSubsequenceOptimized(String text1, String text2) {
        // 더 짧은 문자열을 text2로 설정하여 반복 횟수 최소화
        if (text1.length() < text2.length()) {
            String temp = text1;
            text1 = text2;
            text2 = temp;
        }
        
        int len1 = text1.length();
        int len2 = text2.length();
        
        // 공간 최적화: 1D 배열 2개만 사용 (이전 행과 현재 행)
        int[] prev = new int[len2 + 1];
        int[] curr = new int[len2 + 1];
        
        for (int i = 1; i <= len1; i++) {
            char c1 = text1.charAt(i-1); // charAt() 호출 최소화
            
            for (int j = 1; j <= len2; j++) {
                char c2 = text2.charAt(j-1);
                
                if (c1 == c2) {
                    // 문자가 같으면: 이전 대각선 값 + 1
                    curr[j] = prev[j-1] + 1;
                } else {
                    // 문자가 다르면: 위쪽(prev[j])과 왼쪽(curr[j-1]) 중 최대값
                    curr[j] = Math.max(prev[j], curr[j-1]);
                }
            }
            
            // 행 교체: 현재 행을 이전 행으로 설정하고, curr 배열 초기화
            int[] temp = prev;
            prev = curr;
            curr = temp;
            // curr 배열은 다음 반복에서 덮어씌워지므로 명시적 초기화 불필요
        }
        
        return prev[len2];
    }

}
