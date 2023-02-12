package tobiasras.webcrawler.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tobiasras.webcrawler.model.Search;
import tobiasras.webcrawler.repository.SearchRepository;

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









}
