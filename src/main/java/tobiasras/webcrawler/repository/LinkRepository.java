package tobiasras.webcrawler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tobiasras.webcrawler.model.Link;

public interface LinkRepository extends JpaRepository<Link, Long> {
}
