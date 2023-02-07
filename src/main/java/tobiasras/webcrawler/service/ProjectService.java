package tobiasras.webcrawler.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tobiasras.webcrawler.model.Project;
import tobiasras.webcrawler.repository.ProjectRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProjectService implements ICrudInterface<Project, Long>{

    private ProjectRepository projectRepository;

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public Project save(Project object) {
        return projectRepository.save(object);
    }

    @Override
    public void delete(Project object) {
        projectRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        projectRepository.deleteById(aLong);
    }

    @Override
    public Optional<Project> findById(Long aLong) {
        return projectRepository.findById(aLong);
    }
}
