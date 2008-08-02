/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.button;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.IAjaxCallDecorator;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;


/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public abstract class AbstractButton extends Panel implements IMenuItem {

	private static final long serialVersionUID = 1L;

	public static final int NO_ORDER = -1;
	
	public static final String LABEL_ID = "label";
	
	private Button link;
		
	private boolean ajaxButton = false;
	
	private int order = NO_ORDER;
	
	/**
	 * Constructor.
	 * @param id
	 */
	public AbstractButton(String id) {
		this(id, false, NO_ORDER);
	}
	
	/**
	 * Constructor.
	 * @param id
	 */
	public AbstractButton(String id, int order) {
		this(id, false, order);
	}
	
	/**
	 * Constructor.
	 * @param id
	 */
	public AbstractButton(String id, boolean ajaxButton) {
		this(id, ajaxButton, NO_ORDER);
	}

	/**
	 * Constructor.
	 * @param id
	 */
	public AbstractButton(String id, boolean ajaxButton, int order) {
		super(id);
		setOutputMarkupId(true);
		this.ajaxButton = ajaxButton;
		link = newLink("link");
		add(link);		
		Image image = newImage("image");
		link.add(image);
		link.add(newLabel(LABEL_ID));
	}
	
	/**
	 * Override this method if you want to rpovide your own implementation of 
	 * the a link (the inner button of the panel).
	 * 
	 * @param id
	 * @return
	 */
	protected Button newLink(String id) {
		if(isAjaxButton()) {
			return new IndicatingAjaxSubmitButton(id) {
				
				private static final long serialVersionUID = 1L;

				@Override
				protected void onSubmit(AjaxRequestTarget target, Form form) {
					AbstractButton.this.onSubmit(target, form);
				}
				
				@Override
				protected IAjaxCallDecorator getAjaxCallDecorator() {
					return AbstractButton.this.getAjaxCallDecorator();
				}
				
				@Override
				public boolean isEnabled() {
					return AbstractButton.this.isEnabled();
				}
				
			};	
		}
		return new Button(id) {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				AbstractButton.this.onSubmit();
			}
			
			@Override
			public boolean isEnabled() {
				return AbstractButton.this.isEnabled();
			}
		};
	}
	
	/**
	 * Callback method for Ajax buttons.
	 * 
	 * @param target
	 * @param form
	 */
	protected void onSubmit(AjaxRequestTarget target, Form form) {
				
	}
	
	/**
	 * Callback method for normal buttons.
	 *
	 */
	public void onSubmit() {
		
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
		Image image = new Image(id) {
			private static final long serialVersionUID = 1L;

			@Override
			protected ResourceReference getImageResourceReference() {
				if(AbstractButton.this.isEnabled()) {
					return getImage();
				} 
				ResourceReference reference = getDisabledImage();
				if(reference != null) {
					return reference;
				}
				return getImage();
			}
			
			@Override
			public boolean isVisible() {
				return (getImage() != null);
			}
		};
		return image;
	}
	
	protected Label newLabel(String id) {
		return new Label(id, getLabel());
	}
	
	protected abstract String getLabel();

	protected abstract ResourceReference getImage();

	/**
	 * Returns the image of the disable button. By default is null which means 
	 * image will be used.
	 * 
	 * @return
	 */
	protected  ResourceReference getDisabledImage() {
		return null;
	}
	
	/**
	 * @return the ajaxButton
	 */
	public boolean isAjaxButton() {
		return ajaxButton;
	}

	/**
	 * @param ajaxButton the ajaxButton to set
	 */
	public void setAjaxButton(boolean ajaxButton) {
		this.ajaxButton = ajaxButton;
	}

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

	/**
	 * @return the link
	 */
	public Button getLink() {
		return link;
	}
}