/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.crud;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class AutoCrudStyler<E extends Serializable> extends CrudStyler<E> {

	private static final long serialVersionUID = 1L;

	/**
	 * @param beanClass
	 */
	public AutoCrudStyler(Class<E> beanClass) {
		super(beanClass);
		init(beanClass);
	}

	/**
	 * Uses reflection to gather information of the fields.
	 * 
	 * @param beanClass
	 */
	private void init(Class<E> beanClass) {		
		List<String> fieldNames = new ArrayList<String>();
		
		Field[] fields  = beanClass.getDeclaredFields();		
		for(Field field: fields) {			
			fieldNames.add(field.getName());
		}
		
		addEditFields(fieldNames);
		addSearchFields(fieldNames);		
		addTableColumns(fieldNames);
	}
}