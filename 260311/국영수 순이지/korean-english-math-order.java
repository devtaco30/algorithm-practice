import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] names = new String[n];
        int[] korean = new int[n];
        int[] english = new int[n];
        int[] math = new int[n];
        for (int i = 0; i < n; i++) {
            names[i] = sc.next();
            korean[i] = sc.nextInt();
            english[i] = sc.nextInt();
            math[i] = sc.nextInt();
        }

        List<Score> scores = new ArrayList<Score>();
        
        for (int i = 0; i < n; i++) {
            Score s = new Score(names[i], korean[i], english[i], math[i]);
            scores.add(s);
        }

        Collections.sort(scores);

        for (Score score : scores){
            score.print();
        }

    }
}

class Score implements Comparable<Score>{
    String name;
    int korean;
    int english;
    int math;

    public Score(String name, int korean, int english, int math){
        this.name = name;
        this.korean = korean;
        this.english = english;
        this.math = math;
    }

    @Override
    public int compareTo(Score o){
        if (this.korean != o.korean) return o.korean - this.korean;
        if (this.english != o.english) return o.english - this.english;
        return o.math - this.math;
    }

    public void print() {
        System.out.printf("%s %d %d %d\n", this.name, this.korean, this.english, this.math);
    }



}