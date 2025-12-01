/**
 * Q. 다음과 같이 영어로 되어 있는 문자열이 있을 때, 이 문자열에서 반복되지 않는 첫번째 문자를 반환하시오. 만약 그런 문자가 없다면 _ 를 반환하시오.
 * 
 * "abadabac" # 반복되지 않는 문자는 d, c 가 있지만 "첫번째" 문자니까 d를 반환해주면 됩니다!
 */

import java.util.ArrayList;
import java.util.List;

public class FindNotRepeatingFirstCharacter {
    public static void main(String[] args) {

        Solution solution = new Solution();
        System.out.println("정답 = d 현재 풀이 값 = " + solution.findNotRepeatingFirstCharacter("abadabac"));
        System.out.println("정답 = c 현재 풀이 값 = " + solution.findNotRepeatingFirstCharacter("aabbcddd"));
        System.out.println("정답 = _ 현재 풀이 값 = " + solution.findNotRepeatingFirstCharacter("aaaaaaaa"));
    }
}

class Solution {
    public char findNotRepeatingFirstCharacter(String str) {
        // 이 부분에 구현하시오.

        // 1. 반복되지 않는 문자를 찾는다. 
        int[] alphabets = new int[26];
        for (char alphabet : str.toCharArray()) { // O(N)
            if (!Character.isLetter(alphabet)) {
                continue;
            }
            char lowerCase = Character.toLowerCase(alphabet);
            alphabets[lowerCase - 'a']++;
        }
        // 2. 0 이 아닌 알파벳 idx 를 보면서, 2 이상이면 반복, 1이면 반복 안되는 문자.

        List<Character> noRepeatingCharacters = new ArrayList<>();

        for (int i = 0; i < 26; i++) { // O(26)
            if (alphabets[i] == 1) {
                noRepeatingCharacters.add((char) (i + 'a'));
            }
        }

        // 그런데, 반복되지 않는 문자가 2개 이상 이라면, 먼저 나온 문자를 반환한다. 인데, str 의 길이만큼  반복하며 찾아야한다.
        char firstNoRepeatingCharacter = '_';
        for (char character : str.toCharArray()) { // O(N)
            if (noRepeatingCharacters.contains(character)) {
                firstNoRepeatingCharacter = character;
                break;
            }
        }
        return firstNoRepeatingCharacter; // 최종적으로는 O(N)
    }
}