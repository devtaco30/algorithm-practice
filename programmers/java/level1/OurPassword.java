import java.util.Set;
import java.util.HashSet;

/**
 * 문제: 둘만의 암호
 * 
 * 문제 설명:
 * 두 문자열 s와 skip, 그리고 자연수 index가 주어질 때, 다음 규칙에 따라 문자열을 만들려 합니다.
 * 
 * 암호 규칙:
 * 1. 문자열 s의 각 알파벳을 index만큼 뒤의 알파벳으로 바꿔줍니다.
 * 2. index만큼의 뒤의 알파벳이 z를 넘어갈 경우 다시 a로 돌아갑니다.
 * 3. skip에 있는 알파벳은 제외하고 건너뜁니다.
 * 
 * 예시:
 * s = "aukks", skip = "wbqd", index = 5일 때
 * - 'a'에서 5만큼 뒤에 있는 알파벳을 찾을 때:
 *   일반적으로는 [b, c, d, e, f]에서 'f'가 되지만,
 *   'b'와 'd'는 skip에 포함되므로 제외합니다.
 *   따라서 [c, e, f, g, h] 순서로 5번째 알파벳은 'h'가 됩니다.
 * - 나머지 "ukks"도 위 규칙대로 바꾸면 "appy"가 되며
 * - 최종 결과는 "happy"가 됩니다.
 * 
 * 입력:
 * - s: 변환할 문자열
 * - skip: 건너뛸 알파벳들
 * - index: 이동할 거리
 * 
 * 출력:
 * - 위 규칙대로 s를 변환한 결과를 return
 */
public class OurPassword {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution("aukks", "wbqd", 5));
    }
}


class Solution {
    public String solution(String s, String skip, int index) {
        StringBuilder result = new StringBuilder();
        
        // skip 알파벳들을 Set에 저장 (빠른 조회를 위해)
        Set<Character> skipSet = new HashSet<>();
        for (char c : skip.toCharArray()) {
            skipSet.add(c);
        }
        
        for (char c : s.toCharArray()) {
            int count = 0;
            char current = c;
            
            // index만큼 뒤로 이동 (skip 알파벳은 제외)
            while (count < index) {
                current++;
                // z를 넘어가면 a로 돌아감
                if (current > 'z') {
                    current = 'a';
                }
                // skip에 포함되지 않은 알파벳만 카운트
                if (!skipSet.contains(current)) {
                    count++;
                }
            }
            
            result.append(current);
        }

        return result.toString();
    }
}
