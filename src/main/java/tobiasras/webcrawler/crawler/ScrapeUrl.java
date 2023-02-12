package tobiasras.webcrawler.crawler;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.HashSet;


public class ScrapeUrl {

    private Connection con;
    private Document page;

    public ScrapeUrl(String url) {
        this.con = Jsoup.connect(url);
        this.page = request();
    }

    private Document request() {
        try {
            Document document = con.get();
            if (con.response().statusCode() == 200) {
                return document;
            } else
                return null;

        } catch (IOException e) {
            return null;
        }
    }

    public HashSet<String> fetchAllUrls(){
        if (page != null){
            HashSet<String> urls = new HashSet<>();

            for(Element link : page.select("a[href]")){
                String url = link.absUrl("href");
                urls.add(url);
            }

            return urls;
        } else
            return null;
    }

}
