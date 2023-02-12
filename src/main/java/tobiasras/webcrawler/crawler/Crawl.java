package tobiasras.webcrawler.crawler;

import java.io.IOException;
import java.util.List;

public interface Crawl {
    String httpStatus(String url) throws IOException;
    List<String> links(String url);


}
