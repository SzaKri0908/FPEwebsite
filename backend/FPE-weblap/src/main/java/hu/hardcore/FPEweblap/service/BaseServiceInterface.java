package hu.hardcore.FPEweblap.service;

import hu.hardcore.FPEweblap.model.BaseEntity;

import java.util.List;

public interface BaseServiceInterface<E extends BaseEntity> {

    E findById(Long id);

    void save(E entity);

    void update(E entity);

    List<E> findAll();

}
