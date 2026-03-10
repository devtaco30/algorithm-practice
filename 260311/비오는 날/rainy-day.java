import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Forecast> forecasts = new ArrayList<>();
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            String date = sc.next();
            String day = sc.next();
            String weather = sc.next();
            Forecast f = new Forecast(date, day, weather);
            forecasts.add(f);
        }

        Collections.sort(forecasts);
        forecasts.stream().filter(f -> f.weather.equals("Rain")).findFirst().ifPresent(f -> f.print());



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
   