/**
 * 
 */
package com.antilia.common.dao.impl;

import java.io.Serializable;
import java.util.List;

import com.antilia.common.dao.IQuerableDao;
import com.antilia.common.query.IQuery;
import com.antilia.common.util.CollectionUtils;
import com.antilia.common.util.QueryUtils;

/**
 * An IQuerableDao which uses a List (or Iterable<E>) as is persistence storage.
 * 
 * The DAO is capable of filtering the list taking into account a query object.
 * 
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 */
public class ListQuerableDao<E extends Serializable> implements IQuerableDao<E> {

	private static final long serialVersionUID = 1L;
	
	
	private List<E> list;
	
	public ListQuerableDao(Iterable<E> iterable) {
		this(CollectionUtils.toList(iterable));
	}
	
	public ListQuerableDao(List<E> list) {
		if(list == null)
			throw new IllegalArgumentException("List should be non-null!");
		this.list = list;
	}
	
	public List<E> findAll(IQuery<E> query) {
		return QueryUtils.findSubList(list, query);
	}
	
	public List<E> findAll(Class<E> beanClass) {
		return list;
	}
	
	public Long count(IQuery<E> query) {
		return new Long(QueryUtils.findSubList(list, query).size());
	}
	
	public E findById(Class<E> beanClass, Serializable key) {
		//TODO: implement this somehow
		return null;
	}
	
	public List<E> findByExample(E bean) {
		//TODO: implement this somehow
		return null;
	};
	/**
	 * @return the list
	 */
	public List<E> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<E> list) {
		this.list = list;
	}
	
	
}
