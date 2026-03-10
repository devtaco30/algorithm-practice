import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {

        try ( BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = Integer.parseInt(br.readLine());
            String[] wordList = new String[n];

            for ( int i = 0; i < n ; i++) {
                wordList[i] = br.readLine();
            }

            Arrays.sort(wordList);

            for (int i = 0; i < n; i++) {
                bw.write(wordList[i] + "\n");
            }
            bw.flush();



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}