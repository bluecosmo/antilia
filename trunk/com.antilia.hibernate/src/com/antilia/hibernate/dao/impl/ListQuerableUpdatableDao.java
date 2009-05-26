/**
 * 
 */
package com.antilia.hibernate.dao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.antilia.hibernate.dao.IQuerableUpdatableDao;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class ListQuerableUpdatableDao<E extends Serializable> extends ListQuerableDao<E> implements IQuerableUpdatableDao<E> {

	private static final long serialVersionUID = 1L;
	
	
	public ListQuerableUpdatableDao(List<E> list) {
		super(list);
	}

	public E add(E element) {
		if(getList().contains(element)) {
			int index = getList().indexOf(element);
			getList().set(index, element);
		} else {
			getList().add(element);
		}
		return element;
			
	}

	public void addAll(Collection<E> elements) {
		if(elements == null || elements.size() == 0)
			return;
		 for(E element: elements) {
			 add(element);
		 }
	}
	
	public E remove(E element) {
		 getList().remove(element);
		 return element;
	}
		 
	 public void removeAll(Collection<E> elements) {
		 if(elements == null || elements.size() == 0)
			 return;
		 for(E element: elements) {
			 remove(element);
		 }
	 }
	 
	 public void update(E element) {
		 add(element);
	 }
	 
	 public void updateAll(Collection<E> elements) {
		 if(elements == null || elements.size() == 0)
			 return;
		 for(E element: elements) {
			 update(element);
		 }
	 }

}