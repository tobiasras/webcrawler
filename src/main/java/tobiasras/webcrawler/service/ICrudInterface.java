package tobiasras.webcrawler.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ICrudInterface<T, ID> {
    List<T> findAll();
    T save(T object);
    void delete(T object);
    void deleteById(ID id);
    Optional<T> findById(ID id);
}


