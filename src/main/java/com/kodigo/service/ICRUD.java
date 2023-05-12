package com.kodigo.service;

import java.util.List;

public interface ICRUD<T, ID> {

	T save(T t) throws Exception;

	T edit(T t) throws Exception;

	List<T> findAll() throws Exception;

	T findById(ID id) throws Exception;

	void deleteById(ID id) throws Exception;
}
