package tobiasras.webcrawler.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tobiasras.webcrawler.crawler.ScrapeLink;
import tobiasras.webcrawler.crawler.Status;
import tobiasras.webcrawler.model.Link;
import tobiasras.webcrawler.model.Search;
import tobiasras.webcrawler.repository.SearchRepository;

import java.io.IOException;
import java.util.*;

@AllArgsConstructor
@Service
public class SearchService{

    private SearchRepository searchRepository;

    public Search save(Search object) {
        return searchRepository.save(object);
    }


    public void deleteById(Long aLong) {
        searchRepository.deleteById(aLong);
    }

    public Optional<Search> findById(Long aLong) {
        return searchRepository.findById(aLong);
    }

    public List<Search> findByInitialUrl(String initURl){
        return searchRepository.findByInitialUrl(initURl);
    }
    public List<Search> findByProjectId(Long projectID){
        return searchRepository.findByProjectId(projectID);
    }


    public List<Search> crawl(List<Search> searches) {
        Status status = new Status();

        List<Link> savedLinks = new ArrayList<>();
        // todo faster search multi threading


        for (Search search: searches){
            String initialUrl = search.getInitialUrl();

            ScrapeLink scrapeLink = new ScrapeLink(initialUrl);
            HashSet<String> urls = scrapeLink.fetchAllUrls();


            List<Link> linksCrawled = new LinkedList<>();
            for (String crawledLink : urls) {

                Link link = new Link();
                link.setUrl(crawledLink);
                link.setLastCrawled(new Date());
                String statusForLink;

                String protocol = getProtocolFromUrl(crawledLink);
                link.setProtocol(protocol);

                if (protocol.equals("https") ||  protocol.equals("http") ) {
                    try {
                        statusForLink = status.fetchResponseCode(crawledLink);
                    } catch (IOException e) {
                        statusForLink = "404";
                    }
                    link.setStatus(statusForLink);
                }

                linksCrawled.add(link);
            }

            search.setLinks(linksCrawled);

            searchRepository.save(search);
        }

        return searches;
    }



    private String getProtocolFromUrl(String url){
        //https://www.rigshospitalet.dk/
        String[] split = url.split(":");
        return split[0];

    }



}
