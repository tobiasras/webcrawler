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

    public List<Link> findByStatus(String status) {
        return linkRepository.findByStatus(status);
    }

    public Project crawl(Project project) throws InterruptedException {
        ListUtilities<String> listUtilities = new ListUtilities<>(); // used for splitting the list

        List<Search> searches = project.getSearches();
        for (Search search : searches) {
            linkRepository.deleteLinksBySearch(search);

            String initialUrl = search.getInitialUrl();
            ScrapeLink scrapeLink = new ScrapeLink(initialUrl);
            HashSet<String> urls = scrapeLink.fetchAllUrls();

            LinkedList<String> linkedList = new LinkedList<>(urls); // hashmap converted to list to split it up
            List<List<String>> listToBeCrawled = listUtilities.splitList(linkedList, 10);


            List<Crawl> threads = new ArrayList<>();
            int index = 0;

            for (List<String> strings : listToBeCrawled) {
                index++;
                Crawl crawl = new Crawl(linkRepository, strings, search, "thread " + index);
                crawl.start();
                threads.add(crawl);
            }

            for (Crawl thread : threads) {
                thread.join();
            }
        }

        Long id = project.getId();

        Optional<Project> byId = projectService.findById(id);

        return byId.orElse(null);
    }


}
