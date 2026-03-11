import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // 이름 키 몸무게 받고 

        List<Spec> specs = new ArrayList<>();
        for (int i =0; i<n; i++) {
            String name = sc.next();
            int h = sc.nextInt();
            int w = sc.nextInt();
            Spec spec = new Spec(name, h, w);
            specs.add(spec);
        }

        Collections.sort(specs);

        for(Spec s : specs){
            s.print();
        }

    }
}

class Spec implements Comparable<Spec>{
    String name;
    int height;
    int weight;

    public Spec(String name, int height, int weight){
        this.name = name;
        this.height = height;
        this.weight = weight;
    }

    public void print(){
        System.out.printf("%s %d %d\n", this.name, this.height, this.weight);
    }
    
    @Override
    public int compareTo(Spec o) {
        return this.height - o.height;
    }
}