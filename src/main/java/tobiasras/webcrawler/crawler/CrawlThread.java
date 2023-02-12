package tobiasras.webcrawler.crawler;

import lombok.AllArgsConstructor;
import tobiasras.webcrawler.model.Link;
import tobiasras.webcrawler.model.Search;
import tobiasras.webcrawler.repository.LinkRepository;

import java.io.IOException;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
public class CrawlThread extends Thread {
    private LinkRepository linkRepository;
    private List<String> urls;
    private Search search;


    @Override
    public void run() {
        for (String crawledLink : urls) {
            Link link = new Link();
            link.setSearch(search);
            link.setUrl(crawledLink);
            link.setLastCrawled(new Date());

            String protocol = getProtocolFromUrl(crawledLink);
            link.setProtocol(protocol);

            String statusForLink;

            UrlCrawl crawl = new UrlCrawl();

            if (protocol.equals("https") || protocol.equals("http")) {
                try {
                    statusForLink = crawl.httpStatus(crawledLink);
                } catch (IOException e) {
                    statusForLink = "404";
                }
                link.setStatus(statusForLink);
            }

            linkRepository.save(link);
        }
    }


    private String getProtocolFromUrl(String url) {
        String[] split = url.split(":");
        return split[0];
    }


}
