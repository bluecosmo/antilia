/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.common.query.transform;

import java.io.Serializable;

import com.antilia.common.query.IOrder;

/**
 * A transformer for IOrder.
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public interface IOrderTransformer<R, B extends Serializable> extends ITransformer<IOrder<B>, R> {

}
