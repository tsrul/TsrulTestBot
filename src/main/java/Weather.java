import org.json.JSONArray;
import org.json.JSONObject;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class Weather {
    //c62c4dd809bfe543ef135d4727afdea8
    public static String getWeather(String message, Model model) throws IOException {
        String adress = "https://api.openweathermap.org/data/2.5/weather?q="+message+"&units=metric&appid=c62c4dd809bfe543ef135d4727afdea8";
        System.out.println(adress);
        URL url = new URL(adress);
        Scanner in = new Scanner((InputStream) url.getContent());
        String result = "";
            while (in.hasNext()){
                result+=in.nextLine();
            }
        JSONObject object = new JSONObject(result);
        model.setName(object.getString("name"));

        JSONObject main = object.getJSONObject("main");
        model.setTemp(main.getDouble("temp"));
        model.setHumidity(main.getDouble("humidity"));

        JSONArray getArray = object.getJSONArray("weather");
        for (int i = 0; i<getArray.length();i++){
            JSONObject obj = getArray.getJSONObject(i);
            model.setIcon((String)obj.get("icon"));
            model.setMain((String)obj.get("main"));
        }

        return "City: "+model.getName()+"\n"+
                "Temperature: "+model.getTemp()+"C\n"+
                "Humidity: "+model.getHumidity()+"\n"+
                model.getMain()+"\n"+
                "http://openweathermap.org/img/wn/"+model.getIcon()+".png";
    }
}
