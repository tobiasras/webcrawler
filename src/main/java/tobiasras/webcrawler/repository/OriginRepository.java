package tobiasras.webcrawler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tobiasras.webcrawler.model.Search;

import java.util.List;

public interface SearchRepository extends JpaRepository<Search, Long> {


    public List<Search> findByProjectId(Long ProjectID);
    public List<Search> findByInitialUrl(String initialUrl);





}
