package tobiasras.webcrawler.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tobiasras.webcrawler.crawler.ScrapeLink;
import tobiasras.webcrawler.crawler.Status;
import tobiasras.webcrawler.model.Link;
import tobiasras.webcrawler.model.Search;
import tobiasras.webcrawler.repository.LinkRepository;

import java.io.IOException;
import java.util.*;


@AllArgsConstructor
@Service
public class LinkService implements ICrudInterface<Link, Long> {

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


}
