import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class Weather {
    //713d0b0050350bcd9363ce3862af0fe0 - token
    public static String getWeather(String msg, WeatherModel model) throws IOException {
        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + msg + "&units=metric&appid=713d0b0050350bcd9363ce3862af0fe0");
        Scanner br = new Scanner((InputStream) url.getContent());
        String res = "";

        while (br.hasNext()) {
            res += br.nextLine();
        }

        JSONObject object = new JSONObject(res);
        model.setName(object.getString("name")); //забираем целиком поле из JSON

        JSONObject mainObject = object.getJSONObject("main"); //вытягиваем из поля main значение (пример в файле JSON, а так же можно найти в URl)
        model.setTemp(mainObject.getInt("temp"));
        model.setHumidity(mainObject.getInt("humidity"));

        JSONArray weatherArray = object.getJSONArray("weather"); //это массив в URL, поэтому используются другие методы
        for (int i = 0; i < weatherArray.length(); i++) {
            JSONObject obj = weatherArray.getJSONObject(i);
            model.setMain(obj.getString("main"));
            model.setMain(obj.getString("main"));
        }
        return "City: " + model.getName() + "\n Temperature: " + model.getTemp() + "\n Humidity: " + model.getHumidity();
    }
}
