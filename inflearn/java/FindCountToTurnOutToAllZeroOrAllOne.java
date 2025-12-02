/**
 * 문제: 0과 1을 모두 같게 만들기
 * 
 * 문제 설명:
 * 0과 1로만 이루어진 문자열이 주어졌을 때, 이 문자열에 있는 모든 숫자를 전부 같게 만들려고 합니다.
 * 
 * 할 수 있는 행동:
 * - 문자열에서 연속된 하나 이상의 숫자를 선택하고 모두 뒤집기
 * - 뒤집기: 1을 0으로, 0을 1로 바꾸는 것
 * 
 * 예시: S = "0001100"
 * 
 * 방법 1:
 * - 전체를 뒤집으면: "1110011" (여전히 0과 1이 섞여있음)
 * 
 * 방법 2:
 * - 4번째 문자부터 5번째 문자까지 뒤집으면: "0000000" → 1번 만에 완료!
 *  * 목표:
 * 주어진 문자열을 모두 0 혹은 모두 1로 같게 만드는 최소 횟수를 반환하세요.
 */

public class FindCountToTurnOutToAllZeroOrAllOne {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println("예시: 0001100");
        System.out.println("  - 문제 설명: 4번째~5번째 문자 뒤집으면 1번 만에 완료");
        System.out.println("  - 현재 풀이 값 = " + solution.findCountToTurnOutToAllZeroOrAllOne("0001100"));
        System.out.println();
        System.out.println("정답 = 1 현재 풀이 값 = " + solution.findCountToTurnOutToAllZeroOrAllOne("0001100"));
        System.out.println("정답 = 7 현재 풀이 값 = " + solution.findCountToTurnOutToAllZeroOrAllOne("0001100110011001100110011001100"));
        System.out.println("정답 = 0 현재 풀이 값 = " + solution.findCountToTurnOutToAllZeroOrAllOne("1111111111111111111111111111111"));
    }
    
}

class Solution {
    public int findCountToTurnOutToAllZeroOrAllOne(String str) {
        // 주어진 str 은 0, 1 로만 이루어져 있음.
        // 연속된 0 혹은 1의 개수를 세어서 , 그 중 가장 작은 값을 반환하면 된다.
        // '연속된' 그룹의 개수가 가장 적은 '그룹'을 뒤집으면 된다. 
        // 그게 최소 횟수.
        int[] groupCnts = new int[2]; // 0 과 1 의 그룹 개수       
        
        char prevChar = str.charAt(0);

        // 첫번째 그룹은 무조건 0 혹은 1 이다.
        int groupIdx = prevChar == '0' ? 0 : 1;
        // 첫번째 그룹 +1 먼저 해준다.
        groupCnts[groupIdx]++;

        for (int i = 1; i < str.length(); i++) { // O(N)

            if ( prevChar != str.charAt(i) ) { // 같지 않으면 이 전 그룹이 긑났다.
                groupIdx = str.charAt(i) == '0' ? 0 : 1;
                groupCnts[groupIdx]++;
                prevChar = str.charAt(i);
            } 
        }

        return Math.min(groupCnts[0], groupCnts[1]);
    }
}