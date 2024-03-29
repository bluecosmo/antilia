/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.crud;

import java.io.Serializable;

import org.apache.wicket.Component;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;

import com.antilia.web.button.AbstractButton;
import com.antilia.web.resources.DefaultStyle;


/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class CreateNewButton<E extends Serializable> extends AbstractButton {

	private static final long serialVersionUID = 1L;

	public CreateNewButton() {
		super("new", true);
	}
	
	/* (non-Javadoc)
	 * @see com.antilia.web.toolbar.AButton#getImage()
	 */
	@Override
	protected ResourceReference getImage() {
		return DefaultStyle.IMG_NEW;
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.toolbar.AButton#getLabel()
	 */
	@Override
	protected String getLabel() {
		return "New";
	}
	
	@Override
	protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
			CRUDPanel<E> crudPanel = getCRUDPanel();
			if(crudPanel != null) {			
				// resets the create panel and sets it as the current panel
				crudPanel.setCurrentPanel(crudPanel.getCreatePanel().reset());
				crudPanel.getSearchPanel().getPageableProvider().clearcache();
				target.addComponent((Component)crudPanel);
			}		
	}
	
	@Override
	protected String getLabelKey() {
		return "CreateNewButton.label";
	}

	@SuppressWarnings("unchecked")
	public CRUDPanel<E> getCRUDPanel() {
		return (CRUDPanel<E> )findParent(CRUDPanel.class);
	}
}
