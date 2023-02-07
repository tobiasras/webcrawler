package tobiasras.webcrawler.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tobiasras.webcrawler.model.Link;
import tobiasras.webcrawler.repository.LinkRepository;

import java.util.List;
import java.util.Optional;


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
