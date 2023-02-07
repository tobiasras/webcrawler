package tobiasras.webcrawler.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import java.util.Collection;
import java.util.Date;

// a crawled link

@Entity
@Data
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;
    private String status;
    private Date lastCrawled;
    private String protocol;


    @ManyToOne()
    @JsonBackReference
    private Search search;


}
