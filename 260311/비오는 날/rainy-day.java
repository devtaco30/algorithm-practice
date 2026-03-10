import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) {

        try(
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            List<Forecast> rains = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                StringTokenizer st2 = new StringTokenizer(br.readLine());
                String date = st2.nextToken();
                String day = st2.nextToken();
                String weather = st2.nextToken();
                if ("Rain".equals(weather)) {
                    rains.add(new Forecast(date, day, weather));
                }
            }

            Collections.sort(rains);
            if (!rains.isEmpty()) rains.get(0).print();
        } catch (IOException e){
            e.printStackTrace();
        }


    }
}

 
class Forecast implements Comparable<Forecast>{
    String date; // yyyy-mm-dd
    String day;
    String weather;

    public Forecast(String date, String day, String weather){
        this.date = date;
        this.day = day;
        this.weather = weather;
    }

    @Override
    public int compareTo(Forecast o) {
        return this.date.compareTo(o.date);
    }

    public void print(){
        System.out.printf("%s %s %s", this.date, this.day, this.weather);
    }
}
   