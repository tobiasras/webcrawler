package tobiasras.webcrawler.crawler;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.LinkedList;

public class UrlCrawl implements Crawl {

    @Override
    public String httpStatus(String urlToCheck) throws IOException {
        URL url;
        try {
            url = new URL(urlToCheck);
        } catch (MalformedURLException e) {
            return "Malformed url";
        }

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.connect();

        int code = connection.getResponseCode();

        connection.disconnect();
        return "" + code;
    }

    @Override
    public LinkedList<String> links(String url) {
        ScrapeUrl scrapeLink = new ScrapeUrl(url);
        HashSet<String> links = scrapeLink.fetchAllUrls();
        return new LinkedList<>(links);
    }
}
