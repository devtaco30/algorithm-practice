import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Agent> agents = new ArrayList<Agent>();

        for (int i = 0; i<5; i++){
            String codeName = sc.next();
            int score = sc.nextInt();
            Agent agent = new Agent(codeName, score);
            agents.add(agent);
        }

        Collections.sort(agents);

        agents.get(0).print();



    }
}

class Agent implements Comparable<Agent>{
    String codeName;
    int score;

    public Agent(String codeName, int score) {
        this.codeName = codeName;
        this.score = score;
    }

    @Override
    public int compareTo(Agent o) {
        return this.score - o.score;
    }

    public void print() {
        System.out.println(codeName + " " + score);
    }
}
