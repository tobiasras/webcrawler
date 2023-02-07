package tobiasras.webcrawler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tobiasras.webcrawler.model.Link;

import java.util.List;

public interface LinkRepository extends JpaRepository<Link, Long> {

    public List<Link> findBySearchId(Long id);
    public List<Link> findByStatus(String status);


}
