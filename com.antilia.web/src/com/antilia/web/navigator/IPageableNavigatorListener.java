/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.navigator;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public interface IPageableNavigatorListener extends INavigatorListener {

	void onFirstPage();
	
	void onNextPage();
	
	void onPreviousPage();
	
	void onLastPage();
	
	void onClear();
	
}
