package tobiasras.webcrawler.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tobiasras.webcrawler.model.Search;
import tobiasras.webcrawler.service.SearchService;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("searches")
public class SearchController {

    private SearchService searchService;

    @GetMapping("/projects/{projectID}")
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
    public ResponseEntity<Search> saveSearch(@RequestBody Search search) {
        Search saved = searchService.save(search);
        return new ResponseEntity<>(saved, HttpStatus.OK);
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
