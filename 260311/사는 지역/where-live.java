import java.util.*;
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

        List<Address> members = new ArrayList<>();
        for (int i = 0; i < n ; i++){
            members.add(new Address(names[i], addresses[i], regions[i]));
        }

        Collections.sort(members);

        members.get(members.size()-1).print();

    }
}

class Address implements Comparable<Address>{
    String name;
    String address;
    String region;

    public Address(String name, String address, String region){
        this.name = name;
        this.address = address;
        this.region = region;
    }

    @Override
    public int compareTo(Address o) {
        return this.name.compareTo(o.name);

    }

    public void print(){
        System.out.printf("name %s\n", this.name);
        System.out.printf("addr %s\n", this.address);
        System.out.printf("city %s\n", this.region);
    }
}