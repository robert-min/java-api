package com.example.memorydb.db;

import com.example.memorydb.entity.Entity;

import java.util.*;
import java.util.stream.Collectors;

abstract public class SimpleDataRepository<T extends Entity, ID extends Long> implements DataRepository<T, ID> {

    private List<T> dataList = new ArrayList<T>();
    private static long index = 0;

    private Comparator<T> sort = new Comparator<T>() {
        @Override
        public int compare(T o1, T o2) {
            return Long.compare(o1.getId(), o2.getId());
        }
    };

    // Create
    @Override
    public T save(T data) {
        if (Objects.isNull(data)) {
            throw new RuntimeException("Data is null");
        }

        // check db existence
        var prevData = dataList.stream()
                .filter(it -> {
                    return it.getId().equals(data.getId());
                }).findFirst();

        if (prevData.isPresent()) {
            // update
            dataList.remove(prevData);
            dataList.add(data);
        } else {
            index++;
            // unique Id
            data.setId(index);
            dataList.add(data);
        }
        return data;
    }

    // Read

    @Override
    public Optional<T> findById(ID id) {
        return dataList.stream()
                .filter(it -> {
                    return ( it.getId().equals(id) );
                }).findFirst();
    }

    @Override
    public List<T> findAll() {
        return dataList.stream()
                .sorted(sort)
                .collect(Collectors.toList());
    }

    // Delete

    @Override
    public void delete(ID id) {
        var deleteEntity = dataList.stream()
                .filter(it -> {
                    return ( it.getId().equals(id) );
                }).findFirst();
        if (deleteEntity.isPresent()) {
            dataList.remove(deleteEntity);
        }
    }
}
