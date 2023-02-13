package tobiasras.webcrawler.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tobiasras.webcrawler.model.Project;
import tobiasras.webcrawler.model.Origin;
import tobiasras.webcrawler.service.ProjectService;
import tobiasras.webcrawler.service.SearchService;

import java.util.*;

@AllArgsConstructor
@RestController
@RequestMapping("/projects/{projectID}/searches")
public class SearchController {

    private SearchService searchService;
    private ProjectService projectService;

    @GetMapping("")
    public ResponseEntity<List<Origin>> searchesByProjectID(@PathVariable Long projectID) {
        List<Origin> byProjectId = searchService.findByProjectId(projectID);
        return new ResponseEntity<>(byProjectId, HttpStatus.OK);
    }

    @GetMapping("/url/{initialUrl}")
    public ResponseEntity<List<Origin>> searchesByInitialUrl(@PathVariable String initialUrl) {
        List<Origin> byInitialUrl = searchService.findByInitialUrl(initialUrl);
        return new ResponseEntity<>(byInitialUrl, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Origin> saveSearch(@RequestBody Origin origin, @PathVariable Long projectID) {
        Optional<Project> byProjectId = projectService.findById(projectID);
        if (byProjectId.isPresent()) {
            Project project = byProjectId.get();
            origin.setProject(project);
            Origin saved = searchService.save(origin);
            return new ResponseEntity<>(saved, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }




    @PatchMapping("")
    public ResponseEntity<Origin> findSearchByID(@RequestBody Origin origin) {
        if (origin.getId() != null) {
            Origin updated = searchService.save(origin);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        searchService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
