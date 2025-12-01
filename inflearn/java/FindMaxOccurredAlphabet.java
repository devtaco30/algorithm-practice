/**
 * 다음과 같은 문자열을 입력받았을 때, 어떤 알파벳이 가장 많이 포함되어 있는지 반환하시오. 
 * (단 최빈값을 가진 알파벳이 여러개일 경우 알파벳 순서가 가장 앞에 위치한 알파벳을 출력하시오)
 * 
 */

class FindMaxOccurredAlphabet {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println("정답 = i 현재 풀이 값 =" + solution.findMaxOccurredAlphabet("hello my name is dingcodingco"));
        System.out.println("정답 = e 현재 풀이 값 =" + solution.findMaxOccurredAlphabet("we love algorithm"));
        System.out.println("정답 = b 현재 풀이 값 =" + solution.findMaxOccurredAlphabet("best of best youtube"));
    }
}

class Solution {
    public char findMaxOccurredAlphabet(String str) {
        int maxCnt = 0;
        char maxAlphabet = ' ';
        // 코드를 구현해보세요!!
        int[] alphabets = new int[26];
        for (char alphabet : str.toCharArray()){
            if (!Character.isLetter(alphabet)) {
                continue;
            }
            char lowerCase = Character.toLowerCase(alphabet);
            alphabets[lowerCase - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if (alphabets[i] > maxCnt) {
                maxCnt = alphabets[i];
                maxAlphabet = (char) (i + 'a');
            }
        }
        return maxAlphabet;
    }
}
