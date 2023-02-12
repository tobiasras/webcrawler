package tobiasras.webcrawler.crawler;

import tobiasras.webcrawler.model.Link;
import tobiasras.webcrawler.model.Search;
import tobiasras.webcrawler.repository.LinkRepository;
import tobiasras.webcrawler.utility.ListUtilities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CrawlProcess {

    public CrawlProcess(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    private final LinkRepository linkRepository;

    public void scrape(Search search) throws InterruptedException {

        linkRepository.deleteLinksBySearch(search);
        String initialUrl = search.getInitialUrl();
        UrlCrawl crawl = new UrlCrawl();
        LinkedList<String> linkedList = crawl.links(initialUrl);

        ListUtilities<String> listUtilities = new ListUtilities<>(); // used for splitting the list
        List<List<String>> listToBeCrawled = listUtilities.splitList(linkedList, 10);
        List<CrawlThread> threads = new ArrayList<>();
        for (List<String> strings : listToBeCrawled) {
            CrawlThread crawlThread = new CrawlThread(linkRepository, strings, search);
            crawlThread.start();
            threads.add(crawlThread);
        }

        for (CrawlThread thread : threads) {
            thread.join();
        }

    }

    public void scrape(LinkedList<Link> listOfLinks){



    }



}
