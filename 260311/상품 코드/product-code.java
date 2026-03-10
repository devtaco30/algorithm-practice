import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {

        try (
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in))
        ) { 

            StringTokenizer st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            String code = st.nextToken();

            Product p1 = new Product("codetree", "50");
            Product p2 = new Product(name, code);

            p1.print();
            p2.print();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}

class Product {
    String name;
    String code;

    public Product(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public void print() {
        System.out.printf("product %s is %s \n", this.code, this.name);
    }
}