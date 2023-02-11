package tobiasras.webcrawler.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tobiasras.webcrawler.model.Project;
import tobiasras.webcrawler.model.Search;
import tobiasras.webcrawler.service.LinkService;
import tobiasras.webcrawler.service.ProjectService;
import tobiasras.webcrawler.service.SearchService;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("projects")
public class ProjectController {
    private ProjectService projectService;
    private SearchService searchService;
    private LinkService linkService;

    @GetMapping("")
    public ResponseEntity<List<Project>> all(){
        return new ResponseEntity<>(projectService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> byID(@PathVariable Long id){
        Optional<Project> byId = projectService.findById(id);
        if (byId.isPresent()){
            return new ResponseEntity<>(byId.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<Project> save(@RequestBody Project project){
        Project save = projectService.save(project);
        return new ResponseEntity<>(save, HttpStatus.OK);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        projectService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
