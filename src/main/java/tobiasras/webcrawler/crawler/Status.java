package tobiasras.webcrawler.crawler;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Status{


    public String fetchResponseCode(String urlToCheck) throws IOException {
        URL url = new URL(urlToCheck);


        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        int code = connection.getResponseCode();

        connection.disconnect();
        return "" + code;
    }


}




