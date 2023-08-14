package com.example.memorydb.db;

import java.util.List;
import java.util.Optional;

public interface DataRepository<T, ID> extends Repository<T, ID> {

    // Crate
    T save(T data);

    // Read
    Optional<T> findById(ID id);

    List<T> findAll();

    // Update

    // Delete
    void delete(ID id);
}
