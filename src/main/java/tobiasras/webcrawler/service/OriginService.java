package tobiasras.webcrawler.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tobiasras.webcrawler.model.Origin;
import tobiasras.webcrawler.repository.OriginRepository;

import java.util.*;

@AllArgsConstructor
@Service
public class SearchService{

    private OriginRepository searchRepository;

    public Origin save(Origin object) {
        return searchRepository.save(object);
    }


    public void deleteById(Long aLong) {
        searchRepository.deleteById(aLong);
    }

    public Optional<Origin> findById(Long aLong) {
        return searchRepository.findById(aLong);
    }

    public List<Origin> findByInitialUrl(String initURl){
        return searchRepository.findByInitialUrl(initURl);
    }
    public List<Origin> findByProjectId(Long projectID){
        return searchRepository.findByProjectId(projectID);
    }









}
