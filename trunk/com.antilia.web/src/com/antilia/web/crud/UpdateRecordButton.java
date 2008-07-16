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
public class UpdateRecordButton<E extends Serializable> extends AbstractButton {

	private static final long serialVersionUID = 1L;

	public UpdateRecordButton() {
		super("save", true);
	}
	
	/* (non-Javadoc)
	 * @see com.antilia.web.toolbar.AButton#getImage()
	 */
	@Override
	protected ResourceReference getImage() {
		return DefaultStyle.IMG_SAVE_ENABLED;
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.toolbar.AButton#getLabel()
	 */
	@Override
	protected String getLabel() {
		return "Update";
	}
	
	@Override
	protected void onSubmit(AjaxRequestTarget target, Form form) {
		CRUDPanel<E> crud = findCRUDPanel();		
		crud.getSearchPanel().getPageableProvider().update(findEditPanel().getCurrentBean());
		target.addComponent((Component)crud);
	}
	
	
	@Override
	public void onSubmit() {
		
	}

	@SuppressWarnings("unchecked")
	private CRUDPanel<E> findCRUDPanel() {
		return (CRUDPanel<E>)findParent(CRUDPanel.class);
	}
	
	@SuppressWarnings("unchecked")
	private EditPanel<E> findEditPanel() {
		return (EditPanel<E>)findParent(EditPanel.class);
	}
}
