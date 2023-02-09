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
            String initialUrl = search.getInitialUrl();

            ScrapeLink scrapeLink = new ScrapeLink(initialUrl);
            HashSet<String> urls = scrapeLink.fetchAllUrls();

            System.out.println("all links" + urls.size());

            LinkedList<String> linkedList = new LinkedList<>(urls);

            List<String> firstHalf = linkedList.subList(0, linkedList.size() / 2);
            List<String> secondHalf = linkedList.subList(linkedList.size() / 2 + 1, linkedList.size());

            System.out.println("first: " + firstHalf.size());
            System.out.println("second: " + secondHalf.size());


            // TODO
            Crawl crawl1 = new Crawl(linkRepository, firstHalf, search, "thread 1");
            Crawl crawl2 = new Crawl(linkRepository, secondHalf, search, "thread 2");

            crawl1.start();
            crawl2.start();

            crawl1.join();
            crawl2.join();
        }



        return byId.orElse(null).getSearches();
    }





}
