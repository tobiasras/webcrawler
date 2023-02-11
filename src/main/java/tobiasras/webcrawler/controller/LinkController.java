package tobiasras.webcrawler.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tobiasras.webcrawler.model.Link;
import tobiasras.webcrawler.model.Project;
import tobiasras.webcrawler.model.Search;
import tobiasras.webcrawler.service.LinkService;
import tobiasras.webcrawler.service.ProjectService;
import tobiasras.webcrawler.service.SearchService;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/links")
public class LinkController {

    private LinkService linkService;
    private ProjectService projectService;

    @GetMapping("/searches/{searchID}")
    public ResponseEntity<List<Link>> bySearchID(@PathVariable Long searchID) {
        List<Link> linksBySearchID = linkService.findBySearchID(searchID);
        return new ResponseEntity<>(linksBySearchID, HttpStatus.OK);
    }

    /*
    @GetMapping("/searches/{searchID}")
    public ResponseEntity<List<Link>> byStatus(@PathVariable Long searchID, @RequestParam String status) {

        List<Link> byStatus = linkService.findByStatus(status);
        return new ResponseEntity<>(byStatus, HttpStatus.OK);
    }
     */

    @PostMapping("/projects/{projectID}")
    public ResponseEntity crawlLinks(@PathVariable Long projectID) {
        Optional<Project> opProject = projectService.findById(projectID);

        ResponseEntity<Project> response;

        if (!opProject.isPresent()) {
            return new ResponseEntity<>("project not found", HttpStatus.NOT_FOUND);
        }

        Project project = opProject.get();
        List<Search> searches = project.getSearches();

        if (searches.size() == 0) {
            return new ResponseEntity<>("no searches in project", HttpStatus.NOT_FOUND);
        }

        try {
            Project crawledProject = linkService.crawl(project);
            return new ResponseEntity<>(crawledProject, HttpStatus.OK);

        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity("internal error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }






    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, @PathVariable String searchID) {
        linkService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
