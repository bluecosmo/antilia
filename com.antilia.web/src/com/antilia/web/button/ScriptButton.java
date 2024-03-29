/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.button;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.IAjaxCallDecorator;
import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;


/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public abstract class ScriptButton extends Panel implements IMenuItem {

	private static final long serialVersionUID = 1L;

	public static final int NO_ORDER = -1;
	
	private int order = NO_ORDER;
	
	/**
	 * Constructor.
	 * @param id
	 */
	public ScriptButton(String id) {
		this(id,  NO_ORDER);
	}
	
	/**
	 * Constructor.
	 * @param id
	 */
	public ScriptButton(String id, int order) {
		super(id);
		WebMarkupContainer link = newLink("link");
		add(link);		
		Image image = newImage("image");
		link.add(image);
		link.add(newLabel("label"));
		
		IModel<String> titleModel = getTitleModel();
		if(titleModel != null) {
			link.add(new AttributeModifier("title", titleModel));
		}
	}
	
	/**
	 * Override this method if you want to rpovide your own implementation of 
	 * the a link (the inner button of the panel).
	 * 
	 * @param id
	 * @return
	 */
	protected WebMarkupContainer newLink(String id) {	
		return new WebMarkupContainer(id) {			
			private static final long serialVersionUID = 1L;
			@Override
			protected void onComponentTag(ComponentTag tag) {
				tag.addBehavior(new SimpleAttributeModifier("onclick", ScriptButton.this.onClickScript().replace('\n' , ' ' )));
				tag.addBehavior(new SimpleAttributeModifier("onmousedown", ScriptButton.this.onMouseDownScript().replace('\n' , ' ' )));
				super.onComponentTag(tag);				
			}
		};
	}
	
	protected IModel<String> getTitleModel() {
		String key = getTitleKey();
		if(key != null) {
			return new ResourceModel(key, getLabel());
		}
		return null;
	}
	
	protected abstract String getTitleKey();
	
	
	protected abstract String onClickScript();
	
	protected String onMouseDownScript() {
		return "return true;";
	}
	
	/**
	 * Overide this method to add your own AjaxCallDecorator.
	 * @return
	 */
	protected IAjaxCallDecorator getAjaxCallDecorator()
	{
		return null;
	}
	
	/**
	 * Override this method  to provide your own image.
	 * 
	 * @param id
	 * @return
	 */
	protected Image newImage(String id) {
		return new Image(id, getImage()) {
			
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isVisible() {
				return getImage() != null;
			}
		};
	}
	
	protected Label newLabel(String id) {
		return new Label(id, getLabel());
	}
	
	protected abstract String getLabel();

	protected abstract ResourceReference getImage();


	/**
	 * @return the order
	 */
	public int getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(int order) {
		this.order = order;
	}
}
