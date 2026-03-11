import java.util.*;
public class Main {
    public static void main(String[] args) {

        List<Spec> specs = new ArrayList<Spec>();
        Scanner sc = new Scanner(System.in);
        int n = 5;
        for (int i = 0; i < n; i++) {
            String name = sc.next();
            int h = sc.nextInt();
            double w = sc.nextDouble();

            specs.add(new Spec(name, h, w));
        }

        Collections.sort(specs, (a, b) -> a.name.compareTo(b.name));
        System.out.println("name");
        for (Spec s : specs) {
            s.print();
        }
        Collections.sort(specs, (a, b) -> b.height - a.height);
        System.out.println("\nheight");
        for (Spec s : specs) {
            s.print();
        }


        
    }
}

class Spec {
    String name;
    int height;
    double weight;

    public Spec(String name, int height, double weight){
        this.name = name;
        this.height = height;
        this.weight = weight;
    }

    public void print(){
        System.out.printf("%s %d %.1f\n", this.name, this.height, this.weight );
    }
    
}
