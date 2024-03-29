/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable.navigation;

import java.io.Serializable;

import org.apache.wicket.ResourceReference;

import com.antilia.web.dialog.DefaultDialog;
import com.antilia.web.dialog.DialogButton;
import com.antilia.web.resources.DefaultStyle;
import com.antilia.web.utils.RequestUtils;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class UnusedColumnsButton<E extends Serializable> extends DialogButton {

	private static final long serialVersionUID = 1L;

	public UnusedColumnsButton() {
		super("unusedColumns");
		setShowAtMousePosition(true);
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.dialog.DialogButton#newDialog(java.lang.String)
	 */
	@Override
	public DefaultDialog newDialog(String id) {
		UnusedColumnsDialog<E> dialog = new UnusedColumnsDialog<E>(id);
		dialog.setResizable(false);
		dialog.setFoldable(false);
		dialog.setModal(true);
		return dialog;
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.button.AbstractButton#getImage()
	 */
	@Override
	protected ResourceReference getImage() {
		if(RequestUtils.isBrowserIeExplorer6())
			return DefaultStyle.IMG_ADD_COLS;
		return DefaultStyle.IMG_ADD_COLS_PNG;
	}
	
	@Override
	protected String getLabelKey() {
		return null;
	}
	
	
	@Override
	protected String getLabel() {
		return null;
	}

}
