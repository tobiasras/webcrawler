package tobiasras.webcrawler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tobiasras.webcrawler.model.Search;

public interface SearchRepository extends JpaRepository<Search, Long> {
}
