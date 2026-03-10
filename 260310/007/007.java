import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();
        
        Code code = new Code(s);
        code.print();
    }
}

class Code {
    String sCode;
    char mPoint;
    int time;

    public Code(String code) {
        String[] splited = code.split(" ");
        this.sCode = splited[0];
        this.mPoint = splited[1].charAt(0);
        this.time = Integer.parseInt(splited[2]);
    }

    public void print() {
        System.out.println("secret code : " + this.sCode);
        System.out.println("meeting point : " + this.mPoint);
        System.out.println("time : " + this.time);
    }
}