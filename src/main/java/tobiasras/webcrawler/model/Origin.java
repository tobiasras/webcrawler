package tobiasras.webcrawler.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Entity
@Data
public class Search {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String initialUrl;

    @OneToMany(mappedBy = "search", cascade = CascadeType.ALL)
    private List<Link> links;

    @ManyToOne()
    @JsonBackReference
    private Project project;

}
