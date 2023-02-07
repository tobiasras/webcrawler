package tobiasras.webcrawler.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tobiasras.webcrawler.model.Link;
import tobiasras.webcrawler.service.LinkService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("links")
public class LinkController {

    private LinkService linkService;

    @GetMapping("/search/{searchID}")
    public ResponseEntity<List<Link>> bySearchID(@RequestParam Long searchID){
        List<Link> linksBySearchID = linkService.findBySearchID(searchID);
        return new ResponseEntity<> (linksBySearchID, HttpStatus.OK);
    }

    // findByStatus
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Link>> byStatus(@RequestParam String status){
        List<Link> byStatus = linkService.findByStatus(status);
        return new ResponseEntity<>(byStatus, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        linkService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }




}
