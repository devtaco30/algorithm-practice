import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        for (int i = 0; i < 5; i++ ){
            print10Stars();
        }
    }

    public static void print10Stars() {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); 
            bw.write("**********");
            bw.newLine();
            bw.flush();
        } catch(IOException e ) {
            e.printStackTrace();
        }
        
    }
}