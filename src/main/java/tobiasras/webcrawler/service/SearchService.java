package tobiasras.webcrawler.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tobiasras.webcrawler.model.Search;
import tobiasras.webcrawler.repository.SearchRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class SearchService implements ICrudInterface<Search, Long> {

    private SearchRepository searchRepository;

    @Override
    public List<Search> findAll() {
        return searchRepository.findAll();
    }

    @Override
    public Search save(Search object) {
        return searchRepository.save(object);
    }

    @Override
    public void delete(Search object) {
        searchRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        searchRepository.deleteById(aLong);
    }

    @Override
    public Optional<Search> findById(Long aLong) {
        return searchRepository.findById(aLong);
    }
}
