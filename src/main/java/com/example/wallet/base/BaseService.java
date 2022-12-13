package com.example.wallet.base;

import java.io.Serializable;
import java.util.List;

public interface BaseService<E extends BaseEntity, ID extends Serializable> {
    E saveOrUpdate(E t);

    void deleteById(ID id);

    void deleteEntity(E t);

    E findById(ID id);

    List<E> findAll();

}
