import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String word1 = sc.next();
        String word2 = sc.next();
        // Please write your code here.

        // 결국 내부 문자가 같은걸로 구성되어 있으면 된다.

        char[] word1Chars = word1.toCharArray();
        Arrays.sort(word1Chars);

        char[] word2Chars = word2.toCharArray();
        Arrays.sort(word2Chars);

        if (word1Chars.length != word2Chars.length) {
            System.out.println("No");
            return;
        }

        for (int i = 0; i < word1Chars.length; i++) {
            if (word1Chars[i] != word2Chars[i]) {
                System.out.println("No");
            }
        }

        System.out.println("Yes");

        
    }
}