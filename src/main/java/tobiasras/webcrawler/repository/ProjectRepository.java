package tobiasras.webcrawler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tobiasras.webcrawler.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
