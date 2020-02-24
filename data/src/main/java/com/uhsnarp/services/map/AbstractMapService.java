package com.uhsnarp.services.map;

import com.uhsnarp.model.BaseEntity;

import java.util.*;

public abstract class AbstractMapService<T extends BaseEntity, ID extends Integer> {

    protected Map<Integer, T> map = new HashMap<>();

    Set<T> findAll() {
        return new HashSet<>(map.values());
    }

    T findById(ID id) {
        return map.get(id);
    }

    T save(T object) {
        if(object!=null){
            if(object.getId() == null){
                object.setId(getNextId());
            }
            map.put(object.getId(),object);
        }else{
            throw new RuntimeException("Onject cannot be null");
        }
        //map.put(id, object);
        return object;
    }

    void deleteById(ID id) {
        map.remove(id);
    }

    void delete(T object) {
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    private Integer getNextId(){
        Integer nextId;
        try{
            nextId = Collections.max(map.keySet()) + 1;
        }catch(NoSuchElementException e){
            nextId = 1;
        }
        return nextId;
    }
}
