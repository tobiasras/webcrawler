package tobiasras.webcrawler.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tobiasras.webcrawler.crawler.Crawl;
import tobiasras.webcrawler.crawler.ScrapeLink;
import tobiasras.webcrawler.crawler.Status;
import tobiasras.webcrawler.model.Link;
import tobiasras.webcrawler.model.Project;
import tobiasras.webcrawler.model.Search;
import tobiasras.webcrawler.repository.LinkRepository;
import tobiasras.webcrawler.utility.ListUtilities;

import java.io.IOException;
import java.util.*;


@AllArgsConstructor
@Service
public class LinkService implements ICrudInterface<Link, Long> {

    private ProjectService projectService;
    private LinkRepository linkRepository;

    @Override
    public List<Link> findAll() {
        return null;
    }

    @Override
    public Link save(Link object) {
        return null;
    }

    @Override
    public void delete(Link object) {

    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public Optional<Link> findById(Long aLong) {
        return Optional.empty();
    }

    public List<Link> findBySearchID(Long aLong) {
        return linkRepository.findBySearchId(aLong);
    }

    public List<Link> findByStatus(String status){
        return linkRepository.findByStatus(status);
    }

    public List<Search> crawl(List<Search> searches, Long projectID) throws InterruptedException {

        Optional<Project> byId = projectService.findById(projectID);

        for (Search search: searches){
            linkRepository.deleteAllBySearch(search);

            String initialUrl = search.getInitialUrl();

            ScrapeLink scrapeLink = new ScrapeLink(initialUrl);
            HashSet<String> urls = scrapeLink.fetchAllUrls();

            LinkedList<String> linkedList = new LinkedList<>(urls);
            ListUtilities<String> listUtilities = new ListUtilities<>();

            List<List<String>> listToBeCrawled = listUtilities.splitList(linkedList, 5);

            List<Crawl> threads = new ArrayList<>();

            for (List<String> strings : listToBeCrawled) {
                Crawl crawl = new Crawl(linkRepository, strings, search);
                crawl.start();
                threads.add(crawl);
            }

            for (Crawl thread : threads) {
                thread.join();
            }
            //crawl1.join();
        }



        return byId.orElse(null).getSearches();
    }





}
