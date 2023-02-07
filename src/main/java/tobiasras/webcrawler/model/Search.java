package tobiasras.webcrawler.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Search {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String initialUrl;


    @OneToMany(mappedBy = "search")
    private HashSet<Link> links;

    @ManyToOne()
    @JsonBackReference
    private Project project;

}
