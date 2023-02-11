package tobiasras.webcrawler.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import tobiasras.webcrawler.model.Link;
import tobiasras.webcrawler.model.Search;

import java.util.List;

public interface LinkRepository extends JpaRepository<Link, Long> {

    public List<Link> findBySearchId(Long id);
    public List<Link> findByStatus(String status);

    @Transactional
    public void deleteLinksBySearch(Search search);


}
