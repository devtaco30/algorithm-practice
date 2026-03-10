import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String uCode = sc.next();
        char lColor = sc.next().charAt(0);
        int time = sc.nextInt();

        ReleaseTarget rt = new ReleaseTarget(uCode, lColor, time);
        rt.print();
    }
}

class ReleaseTarget {
    String code;
    char color;
    int sec;

    public ReleaseTarget(String code, char color, int sec) {
        this.code = code;
        this.color = color;
        this.sec = sec;
    }

    public void print() {
        System.out.println("code : " + this.code);
        System.out.println("color : " + this.color);
        System.out.println("second : " + this.sec);
    }
}