/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.dynamicdrive;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.JavascriptPackageResource;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.Model;

/**
 * This component is based on this Menu
 * 
 * http://www.dynamicdrive.com/style/csslibrary/item/jquery_multi_level_css_menu_2/
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class Menubar extends Panel implements IMenu {

	List<IMenuItem> items = new ArrayList<IMenuItem>();
	
	private static final ResourceReference JQUERY = new ResourceReference(Menubar.class, "jquery-1.3.2.min.js");
	
	private static final ResourceReference SLIDEMENU = new ResourceReference(Menubar.class, "jqueryslidemenu.js");
	
	protected static final ResourceReference SLIDEMENU_CSS = new ResourceReference(Menubar.class, "jqueryslidemenu.css");
	
	protected static final ResourceReference SLIDEMENU_CSS_BLUE = new ResourceReference(Menubar.class, "jqueryslidemenu_blue.css");
	
	
	private static final ResourceReference RIGHT_IMG = new ResourceReference(Menubar.class, "right.gif");
	
	private static final ResourceReference DOWN_IMG = new ResourceReference(Menubar.class, "down.gif");
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private WebMarkupContainer toolbar;
	
	private RepeatingView itemsView;
	
	/**
	 * @param id
	 */
	public Menubar(String id) {
		super(id);
		
		setRenderBodyOnly(true);
		
		add(JavascriptPackageResource.getHeaderContribution(JQUERY));
		add(JavascriptPackageResource.getHeaderContribution(newMenuJavaScript()));		
		add(CSSPackageResource.getHeaderContribution(newMenuCss()));
			
		toolbar = new WebMarkupContainer("toolbar");				
		toolbar.setOutputMarkupId(true);
		
		add(toolbar);
		
		itemsView = new RepeatingView("items");		
		toolbar.addOrReplace(itemsView);
	}
	
	/**
	 * 
	 * @return
	 */
	protected ResourceReference newMenuJavaScript() {
		return SLIDEMENU;
	}
	
	/**
	 * 
	 * @return
	 */
	protected ResourceReference newMenuCss() {
		return SLIDEMENU_CSS;
	}
	
	
	protected ResourceReference newDownImage() {
		return DOWN_IMG;
	}
	
	protected ResourceReference newRightImage() {
		return RIGHT_IMG;
	}
	
	@Override
	protected void onBeforeRender() {
				
		itemsView.removeAll();
		
		for(IMenuItem item: this.items) {
			if(item instanceof Component) {
				itemsView.add((Component)item);
			}
		}
		
		Label script = new Label("script", new Model<String>()) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onComponentTagBody(MarkupStream markupStream, ComponentTag openTag) {
				String toolbarId =  toolbar.getMarkupId();
				StringBuffer sb = new StringBuffer();
				sb.append("var arrowimages={down:['downarrowclass', '");
				sb.append(urlFor(Menubar.this.newDownImage()));
				sb.append("', 23], right:['rightarrowclass', '");
				sb.append(urlFor(Menubar.this.newRightImage()));
				sb.append("']};");
				sb.append("jqueryslidemenu.buildmenu(\"");
				sb.append(toolbarId);
				sb.append("\", arrowimages);");				
				replaceComponentTagBody(markupStream, openTag, sb.toString());
			}
		};
		toolbar.addOrReplace(script);
		
		super.onBeforeRender();
	}
	
	
	public IMenu addItem(IMenuItem item) {
		items.add(item);
		return this;
	}
	
	public IMenu removeItem(IMenuItem item) {
		items.add(item);
		return this;
	}
	
	public boolean isOnTop() {
		return true;
	}
	
}
