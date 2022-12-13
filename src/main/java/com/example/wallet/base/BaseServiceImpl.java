package com.example.wallet.base;

import com.example.wallet.exception.BadRequestException;
import com.example.wallet.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@RequiredArgsConstructor
public abstract class BaseServiceImpl<E extends BaseEntity, ID extends Serializable,
        R extends JpaRepository<E, ID>> implements BaseService<E, ID> {

    protected final R repository;
    @Transactional
    @Override
    public E saveOrUpdate(E e) {
        try {
            return repository.save(e);
        }catch (OptimisticLockingFailureException ex){
            throw new BadRequestException("checked version save or update");
        }
    }

    @Override
    public void deleteById(ID id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("your entity not found");
        }
    }

    @Override
    public void deleteEntity(E e) {
        repository.delete(e);
    }

    @Override
    public E findById(ID id) {
        return repository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("your entity not found");
        });
    }

    @Override
    public List<E> findAll() {
        return repository.findAll();
    }
}
