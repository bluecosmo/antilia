/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.provider;

import java.io.Serializable;

import com.antilia.common.query.IQuery;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public interface IQuerable<E extends Serializable>  {
	
	IQuery<E> getQuery();
	
	 void setQuery(IQuery<E> query);
	
}
