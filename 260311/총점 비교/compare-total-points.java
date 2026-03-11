import java.util.*;
public class Main {
    public static void main(String[] args) {
        List<Score> scores = new ArrayList<Score>();
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            String name = sc.next();
            int score1 = sc.nextInt();
            int score2 = sc.nextInt();
            int score3 = sc.nextInt();
            Score s = new Score(name, score1, score2, score3);
            scores.add(s);
        }

        Collections.sort(scores);

        for(Score s : scores){
            s.print();
        }

        
        

        
    }
}

class Score implements Comparable<Score>{
    String name; 
    int s1;
    int s2;
    int s3;
    int total;

    public Score(String n, int s1, int s2, int s3) {
        this.name = n;
        this.s1 =s1;
        this.s2 = s2;
        this.s3 = s3;
        this.total = s1+s2+s3;
    }

    @Override
    public int compareTo(Score o){
        return this.total - o.total;
    }

    public void print() {
        System.out.printf("%s %d %d %d\n", this.name, this.s1, this.s2, this.s3);
    }
}