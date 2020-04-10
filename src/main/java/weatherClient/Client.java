package weatherClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import common.HttpUtil;
import common.ResponseWrapper;

import java.io.IOException;

public class Client {
    final String baseUrl = "http://api.openweathermap.org/";
    String apiKey;
    HttpUtil httpUtil;

    public Client(String apiKey, HttpUtil httpUtil) {
        this.apiKey = apiKey;
        this.httpUtil = httpUtil;
    }

    public City getCity(String cityName){
        City city = null;
        String url = baseUrl+"data/2.5/weather?q="+cityName+"&appid="+apiKey;
        ResponseWrapper response = httpUtil.get(url);
        if(response.getSuccess() && response.getStatus() == 200){
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                city = objectMapper.readValue(response.getContent(), City.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return city;
    }
}
