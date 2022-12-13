package com.example.wallet.base;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

@RequiredArgsConstructor
public abstract class BaseRestController<D extends BaseDto,
        E extends BaseEntity,
        M extends BaseMapper<E, D>,
        S extends BaseService<E, ID>,
        ID extends Serializable> {

    protected final S service;
    protected final M mapper;


    @PutMapping
    public ResponseEntity<D> update(@Valid @RequestBody D d) {
        E e = mapper.convertDToE(d);
        return ResponseEntity.ok(
                mapper.convertEToD(
                        service.saveOrUpdate(e)
                )
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<D> findById(@PathVariable ID id) {
        return ResponseEntity.ok(
                mapper.convertEToD(
                        service.findById(id)
                )
        );
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable ID id) {
        service.findById(id);
        service.deleteById(id);
    }

    @GetMapping
    public ResponseEntity<List<D>> findAll() {
        List<E> all = service.findAll();
        return ResponseEntity.ok(
                mapper.convertListEToD(all)
        );
    }
}
