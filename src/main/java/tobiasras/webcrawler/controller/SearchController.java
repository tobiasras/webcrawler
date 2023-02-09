package tobiasras.webcrawler.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tobiasras.webcrawler.crawler.ScrapeLink;
import tobiasras.webcrawler.crawler.Status;
import tobiasras.webcrawler.model.Link;
import tobiasras.webcrawler.model.Project;
import tobiasras.webcrawler.model.Search;
import tobiasras.webcrawler.service.ProjectService;
import tobiasras.webcrawler.service.SearchService;

import java.io.IOException;
import java.util.*;

@AllArgsConstructor
@RestController
@RequestMapping("/projects/{projectID}/searches")
public class SearchController {

    private SearchService searchService;
    private ProjectService projectService;

    @GetMapping("")
    public ResponseEntity<List<Search>> searchesByProjectID(@PathVariable Long projectID) {
        List<Search> byProjectId = searchService.findByProjectId(projectID);
        return new ResponseEntity<>(byProjectId, HttpStatus.OK);
    }

    @GetMapping("/url/{initialUrl}")
    public ResponseEntity<List<Search>> searchesByInitialUrl(@PathVariable String initialUrl) {
        List<Search> byInitialUrl = searchService.findByInitialUrl(initialUrl);
        return new ResponseEntity<>(byInitialUrl, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Search> saveSearch(@RequestBody Search search, @PathVariable Long projectID) {
        Optional<Project> byProjectId = projectService.findById(projectID);
        if (byProjectId.isPresent()) {
            Project project = byProjectId.get();
            search.setProject(project);
            Search saved = searchService.save(search);
            return new ResponseEntity<>(saved, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }




    @PatchMapping("")
    public ResponseEntity<Search> findSearchByID(@RequestBody Search search) {
        if (search.getId() != null) {
            Search updated = searchService.save(search);
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
