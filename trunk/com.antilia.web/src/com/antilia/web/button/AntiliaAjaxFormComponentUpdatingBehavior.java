/**
 * 
 */
package com.antilia.web.button;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.IAjaxCallDecorator;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;

import com.antilia.web.ajax.AntiliaAjaxCallDecorator;
import com.antilia.web.ajax.IDialogFinder;
import com.antilia.web.dialog.IDialogScope;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 */
public abstract class AntiliaAjaxFormComponentUpdatingBehavior extends AjaxFormComponentUpdatingBehavior implements IDialogFinder {

	private static final long serialVersionUID = 1L;

	/*
	public class AntiliaAjaxCallDecorator implements IAjaxCallDecorator {

		private static final long serialVersionUID = 1L;

		public CharSequence decorateOnFailureScript(CharSequence script) {
			IDialogScope dialogScope = AntiliaAjaxFormComponentUpdatingBehavior.this.findParentDialog();
			String errorMessage = ";alert('"+AntiliaAjaxFormComponentUpdatingBehavior.this.getComponent().getString("ServerDown", null, "Server Down!")+"');";
			if(dialogScope != null) {
				return script + ";" + VeilResources.Javascript.Generic.toggle(dialogScope.getDialogId()) + errorMessage ;
			} 
			return script + ";" + VeilResources.Javascript.Generic.toggle("AT_body") + errorMessage;
		}
		
		public CharSequence decorateOnSuccessScript(CharSequence script) {
			IDialogScope dialogScope = AntiliaAjaxFormComponentUpdatingBehavior.this.findParentDialog();
			if(dialogScope != null) {
				return script + ";" + VeilResources.Javascript.Generic.toggle(dialogScope.getDialogId()) + ";" ;
			}
			return script + ";" + VeilResources.Javascript.Generic.toggle("AT_body") + ";";
		}
		
		public CharSequence decorateScript(CharSequence script) {
			IDialogScope dialogScope = AntiliaAjaxFormComponentUpdatingBehavior.this.findParentDialog();
			if(dialogScope != null) {
				return VeilResources.Javascript.Generic.show(dialogScope.getDialogId()) + ";" + script;
			}
			return VeilResources.Javascript.Generic.show("AT_body") + ";" + script;
		}
	};
	*/

	public IDialogScope findParentDialog() {
		return (IDialogScope)getComponent().findParent(IDialogScope.class);
	}

	public Component getDefiningComponent() {
		return getComponent();
	}
		
	private IAjaxCallDecorator decorator;
	/**
	 * @param event
	 */
	public AntiliaAjaxFormComponentUpdatingBehavior(String event) {
		super(event);
	}
	
	
	@Override
	protected IAjaxCallDecorator getAjaxCallDecorator() {
		if(decorator == null) 		
			decorator = new AntiliaAjaxCallDecorator(this);
		return decorator;
	}
	

}
