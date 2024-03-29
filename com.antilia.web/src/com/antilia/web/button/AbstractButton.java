/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.button;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.IAjaxCallDecorator;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;

import com.antilia.web.ajax.AntiliaAjaxCallDecorator;
import com.antilia.web.ajax.IAjaxCallDecoratorProvider;
import com.antilia.web.ajax.IDialogFinder;
import com.antilia.web.dialog.IDialogScope;
import com.antilia.web.dialog.IVeilScope;
import com.antilia.web.toolbar.IToolbarItem;


/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public abstract class AbstractButton extends Panel implements IMenuItem, IToolbarItem, IDialogFinder, IAjaxCallDecoratorProvider {

	private static final long serialVersionUID = 1L;

	public static final int NO_ORDER = -1;
	
	public static final String LABEL_ID = "label";
	
	private Button link;
		
	private boolean ajaxButton = false;

	private int order = NO_ORDER;
	
	private IDialogScope dialogScope;
	
	private IAjaxCallDecoratorProvider provider = null;
	
	
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
		
	}
	
	@Override
	protected void onBeforeRender() {
		if(!hasBeenRendered()) {
			IModel<String> title = getTitleModel();
			if(title != null) {
				link.add(new AttributeModifier("title", title));
			}
			Image image = newImage("image");
			link.add(image);
			link.add(newLabel(LABEL_ID));
		}
		super.onBeforeRender();
	}
	
	/**
	 * Override this method if you want to provide your own implementation of 
	 * the a link (the inner button of the panel).
	 * 
	 * @param id
	 * @return
	 */
	protected Button newLink(String id) {
		if(isAjaxButton()) {
			return new AjaxButton(id) {
				
				private static final long serialVersionUID = 1L;

				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					AbstractButton.this.onSubmit(target, form);
				}
				
				@Override
				protected IAjaxCallDecorator getAjaxCallDecorator() {
					return AbstractButton.this.findAjaxCallDecorator();					
				}
				
				@Override
				public boolean isEnabled() {
					return AbstractButton.this.isEnabled();
				}
				
				@Override
				protected void onError(AjaxRequestTarget target, Form<?> form) {
					AbstractButton.this.onError(target, form);
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
	
	public Form<?> getForm() {
		return getLink().getForm();
	}
	
	/**
	 * Callback method for Ajax buttons.
	 * 
	 * @param target
	 * @param form
	 */
	protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				
	}
	
	protected void onError(AjaxRequestTarget target, Form<?> form)
	{
		
	}
	
	/**
	 * Callback method for normal buttons.
	 *
	 */
	public void onSubmit() {
		
	}
	
	protected IAjaxCallDecorator findAjaxCallDecorator() {
		if(provider == null) {
			provider = findParent(IAjaxCallDecoratorProvider.class);
			if(provider == null) {
				provider = this;				
			}
		}
		return provider.getAjaxCallDecorator();
	}
	
	/**
	 * Overide this method to add your own AjaxCallDecorator.
	 * @return
	 */
	public IAjaxCallDecorator getAjaxCallDecorator()
	{						
		return new AntiliaAjaxCallDecorator(this);
	}
	
	
	public IDialogScope findParentDialog() {
		return findParent(IDialogScope.class);
	}
	
	public IVeilScope findVeilScope() {
		return findParent(IVeilScope.class);
	}
	
	
	public Component getDefiningComponent() {
		return this;
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
		IModel<String> model = getLabelModel();
		if(model != null)
			return new Label(id, model);
		else 
			return new Label(id, getLabel());
	}
	
	/**
	 * Override this method to get a different resource model.
	 * @return
	 */
	protected IModel<String> getLabelModel() {
		String key = getLabelKey();
		if(key != null)
			return new ResourceModel(key, getLabel());
		return null;
	}
	
	/**
	 * Creates a new title model.
	 * 
	 * @return
	 */
	protected IModel<String> getTitleModel() {
		String key = getTitleKey();
		if(key != null)
			return new ResourceModel(key, getLabel());
		return null;
	}
	
	
	protected abstract String getLabelKey();
	
	protected abstract String getLabel();

	protected abstract ResourceReference getImage();
		
	protected String getTitleKey() {
		return this.getClass().getSimpleName()+".title";
	}
	
	

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

	public IDialogScope getDialogScope() {
		if(dialogScope == null)
			dialogScope = findParentDialog();
		return dialogScope;
	}
}
