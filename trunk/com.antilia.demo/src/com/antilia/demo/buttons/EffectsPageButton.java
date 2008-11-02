/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.buttons;

import org.apache.wicket.markup.html.WebMarkupContainer;

import com.antilia.demo.Index;
import com.antilia.demo.effect.EffectsPanel;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class EffectsPageButton extends AppNavigationButton {

	private static final long serialVersionUID = 1L;

	/**
	 * @param page
	 */
	public EffectsPageButton(Index page) {
		super("effects",page);
	}

	/* (non-Javadoc)
	 * @see com.antilia.test.AppNavigationButton#getBodyPanel(java.lang.String)
	 */
	@Override
	public WebMarkupContainer getBodyPanel(String id) {
		return new EffectsPanel(id);
	}
	
	@Override
	protected String getLabel() {
		return "Effects";
	}

	@Override
	protected String getLabelKey() {
		return null;
	}
	
	@Override
	protected String getTitleKey() {
		return null;
	}
}
