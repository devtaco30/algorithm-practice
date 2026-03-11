import java.util.*;
public class Main {
    public static void main(String[] args) {
        List<Spec> specs = new ArrayList<Spec>();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int h = sc.nextInt();
            int w = sc.nextInt();
            specs.add(new Spec(i+1, h, w) );
        }

        Collections.sort(specs);

        for(Spec s : specs){
            s.print();
        }

    }
}

class Spec implements Comparable<Spec>{
    int number;
    int height;
    int weight;

    public Spec(int number, int height, int weight){
        this.number = number;
        this.height = height;
        this.weight = weight;
    }

    public void print(){
        System.out.printf("%d %d %d\n", this.height, this.weight, this.number);
    }
    
    @Override
    public int compareTo(Spec o) {
        if (this.height!=o.height) return o.height - this.height;
        if (this.weight!=o.weight) return o.weight - this.weight;
        return this.number - o.number;
    }
}