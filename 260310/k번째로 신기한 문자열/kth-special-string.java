import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // n 개의 단어
        int k = sc.nextInt(); // 정렬후 출력할 인덱스 (k - 1)
        String t = sc.next(); // StartWith
        String[] words = new String[n];
        for (int i = 0; i < n; i++) {
            words[i] = sc.next();
        }

        List<String> wordList = new ArrayList<String>();

        for (int i = 0; i < n ; i++) {
            if ( words[i].startsWith(t)) {
                wordList.add(words[i]);
            }
        }

        Collections.sort(wordList);

        System.out.println(wordList.get(k-1));


        
    }
}