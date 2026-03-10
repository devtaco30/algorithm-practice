import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] names = new String[n];
        String[] addresses = new String[n];
        String[] regions = new String[n];
        for (int i = 0; i < n; i++) {
            names[i] = sc.next();
            addresses[i] = sc.next();
            regions[i] = sc.next();
        }
        // Please write your code here.
    }
}

class Address {
    String name;
    String streeNumber;
    String region;

    public Address(String name, String streetNumber, String region){
        this.name = name;
        this.streetNumber = streetNumber;
        this.region = region;
    }

    public void print(){
        System.out.prinf("name %s\n", this.name);
        System.out.prinf("name %s\n", this.name);
    }
}