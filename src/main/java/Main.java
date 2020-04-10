import common.HttpUtil;
import weatherClient.City;
import weatherClient.Client;
import weatherClient.Sys;

public class Main {
    public static void main(String[] args){
        HttpUtil httpUtil = new HttpUtil();
        Client weatherClient = new Client("cbd1bec157087caf69ee872caf55f8f7", httpUtil);
        String [] citiesNames = {"tel%20aviv", "singapore", "auckland", "ushuaia", "miami","london",
        "berlin", "reykjavik", "cape%20town", "kathmandu"};
        Long minDaylight = 0L;
        Long maxDayLight = 0L;
        City mostSunnyCity = null;
        City leastSunnyCity = null;
        for(String city :  citiesNames){
            City currentCity = weatherClient.getCity(city);
            if(currentCity == null){
                System.out.println(city);
                continue;
            }
            Long dayLight = currentCity.getDayLight();
            if(dayLight >= maxDayLight){
                maxDayLight = dayLight;
                mostSunnyCity = currentCity;
            }
            if(dayLight <= minDaylight || minDaylight == 0){
                minDaylight = dayLight;
                leastSunnyCity = currentCity;
            }
        }
        if(mostSunnyCity != null){
            System.out.println("The most sunny city is "+mostSunnyCity.getName()+" with temperature "+mostSunnyCity.getMain().getTemp());
        }
        if(leastSunnyCity != null){
            System.out.println("The least sunny city is "+leastSunnyCity.getName()+" with temperature "+leastSunnyCity.getMain().getTemp());
        }
    }
}
