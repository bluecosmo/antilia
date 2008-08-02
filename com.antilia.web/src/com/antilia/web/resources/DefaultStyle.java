/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.resources;

import org.apache.wicket.ResourceReference;

import com.antilia.web.resources.images.ImgDummy;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class DefaultStyle {

	public static ResourceReference CSS_MAIN = new ResourceReference(DefaultStyle.class, "main.css");
	public static ResourceReference CSS_TABLE = new ResourceReference(DefaultStyle.class, "table.css");
	public static ResourceReference CSS_DIALOG = new ResourceReference(DefaultStyle.class, "dialog.css");
	
	public static ResourceReference JS_COMMON = new ResourceReference(DefaultStyle.class, "common.js");
	public static ResourceReference JS_TABLE = new ResourceReference(DefaultStyle.class, "table.js");
	public static ResourceReference JS_DIALOG = new ResourceReference(DefaultStyle.class, "dialog.js");
	// scriptaculous JSs
	public static ResourceReference JS_PROTOTYPE = new ResourceReference(DefaultStyle.class, "prototype.js");
	public static ResourceReference JS_BUILDER = new ResourceReference(DefaultStyle.class, "builder.js");
	public static ResourceReference JS_EFFECT = new ResourceReference(DefaultStyle.class, "effects.js");
	public static ResourceReference JS_DRAGDROP = new ResourceReference(DefaultStyle.class, "dragdrop.js");
	public static ResourceReference JS_CONTROL = new ResourceReference(DefaultStyle.class, "controls.js");
	public static ResourceReference JS_SLIDER = new ResourceReference(DefaultStyle.class, "slider.js");
	
	public static ResourceReference JS_DropDownMenuX = new ResourceReference(DefaultStyle.class, "DropDownMenuX.js");	
	public static ResourceReference JS_ie5 = new ResourceReference(DefaultStyle.class, "ie5.js");
	
	public static ResourceReference CC_menu = new ResourceReference(DefaultStyle.class, "menu.css");
	
	public static ResourceReference IMG_CANCEL = new ResourceReference(ImgDummy.class, "cancel.gif");
	
	public static ResourceReference IMG_CLOSE = new ResourceReference(ImgDummy.class, "close_bule.gif");
	
	public static ResourceReference IMG_BOTTOM = new ResourceReference(ImgDummy.class, "bottom.gif");
	public static ResourceReference IMG_OK = new ResourceReference(ImgDummy.class, "ok.gif");
	public static ResourceReference IMG_TRANSPARENT = new ResourceReference(ImgDummy.class, "transparent.gif");
	public static ResourceReference IMG_FOLD = new ResourceReference(ImgDummy.class, "fold.gif");
	public static ResourceReference IMG_REVERT_SELECTED = new ResourceReference(ImgDummy.class, "revert_small.png");
	
	public static ResourceReference IMG_VERTICAL_MENU  = new ResourceReference(ImgDummy.class, "vertical-menu.gif");
	
	public static ResourceReference IMG_NEXT = new ResourceReference(ImgDummy.class, "mv_next.gif");
	public static ResourceReference IMG_PREVIOUS = new ResourceReference(ImgDummy.class, "mv_previous.gif");
	public static ResourceReference IMG_LAST = new ResourceReference(ImgDummy.class, "mv_last.gif");
	public static ResourceReference IMG_FIRST = new ResourceReference(ImgDummy.class, "mv_first.gif");
	
	
	public static ResourceReference IMG_NEXT_ENABLED  = new ResourceReference(ImgDummy.class, "next_enabled.png");
	public static ResourceReference IMG_PREVIOUS_ENABLED = new ResourceReference(ImgDummy.class, "previous_enabled.png");
	public static ResourceReference IMG_LAST_ENABLED  = new ResourceReference(ImgDummy.class, "last_enabled.png");
	public static ResourceReference IMG_FIRST_ENABLED  = new ResourceReference(ImgDummy.class, "first_enabled.png");
	
	public static ResourceReference IMG_NEXT_DISABLED = new ResourceReference(ImgDummy.class, "next_disabled.png");
	public static ResourceReference IMG_PREVIOUS_DISABLED = new ResourceReference(ImgDummy.class, "previous_disabled.png");
	public static ResourceReference IMG_LAST_DISABLED  = new ResourceReference(ImgDummy.class, "last_disabled.png");
	public static ResourceReference IMG_FIRST_DISABLED  = new ResourceReference(ImgDummy.class, "first_disabled.png");
	
	public static ResourceReference IMG_SORT_ASC = new ResourceReference(ImgDummy.class, "bullet_arrow_up.png");
	public static ResourceReference IMG_SORT_DES = new ResourceReference(ImgDummy.class, "bullet_arrow_down.png");
	
	public static ResourceReference IMG_REFRESH = new ResourceReference(ImgDummy.class, "refresh_small.png");
	public static ResourceReference IMG_DOWN = new ResourceReference(ImgDummy.class, "bullet_arrow_down.png");
	
	
	public static ResourceReference IMG_BACK = new ResourceReference(ImgDummy.class, "arrow_back.gif");
	public static ResourceReference IMG_EDIT = new ResourceReference(ImgDummy.class, "form_edit.png");
	public static ResourceReference IMG_NEW = new ResourceReference(ImgDummy.class, "form_add.png");	
	public static ResourceReference IMG_DELETE = new ResourceReference(ImgDummy.class, "form_delete.png");
	public static ResourceReference IMG_VIEW = new ResourceReference(ImgDummy.class, "form_view.png");
	
	
	public static ResourceReference IMG_LOAD = new ResourceReference(ImgDummy.class, "load.png");
	
	
	public static ResourceReference IMG_CHECKBOX_CHECKED = new ResourceReference(ImgDummy.class, "checkboxChecked.gif");
	public static ResourceReference IMG_CHECKBOX_UNCHECKED = new ResourceReference(ImgDummy.class, "checkboxUnchecked.gif");
	
	public static ResourceReference IMG_RESIZE = new ResourceReference(ImgDummy.class, "resize.gif");
	public static ResourceReference IMG_SEPARATOR = new ResourceReference(ImgDummy.class, "separator.gif");
	
	public static ResourceReference IMG_SAVE_ENABLED = new ResourceReference(ImgDummy.class, "saveEnabled.png");
	
	
	public static ResourceReference IMG_MINIMIZED = new ResourceReference(ImgDummy.class, "minimized.gif");
}