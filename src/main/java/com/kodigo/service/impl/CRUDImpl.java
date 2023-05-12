package com.kodigo.service.impl;

import com.kodigo.exception.ModelNotFoundException;
import com.kodigo.repo.IGenericRepo;
import com.kodigo.service.ICRUD;
import com.kodigo.util.Constant;

import java.util.List;

public abstract class CRUDImpl<T, ID> implements ICRUD<T, ID> {

    protected abstract IGenericRepo<T, ID> getRepo();

    @Override
    public T save(T t) throws Exception {
        return getRepo().save(t);
    }

    @Override
    public T edit(T t) throws Exception {
        return getRepo().save(t);
    }

    @Override
    public List<T> findAll() throws Exception {
        return getRepo().findAll();
    }

    @Override
    public T findById(ID id) throws Exception {
        T t = getRepo().findById(id).orElse(null);

        if (t == null) {
            throw new ModelNotFoundException(Constant.ID_NOT_FOUND + id);
        }

        return t;
    }

    @Override
    public void deleteById(ID id) throws Exception {

        T t = getRepo().findById(id).orElse(null);
        if (t == null) {
            throw new ModelNotFoundException(Constant.ID_NOT_FOUND + id);
        }

        getRepo().deleteById(id);
    }
}
