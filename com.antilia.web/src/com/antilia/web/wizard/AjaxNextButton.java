/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.antilia.web.wizard;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.wizard.IWizardModel;
import org.apache.wicket.extensions.wizard.IWizardStep;
import org.apache.wicket.markup.html.form.Form;

import com.antilia.web.resources.DefaultStyle;


/**
 * Models a next button in the wizard. When pressed, it calls {@link IWizardStep#applyState()} on
 * the active wizard step, and then moves the wizard state to the next step of the model by calling
 * {@link IWizardModel#next() next} on the wizard's model.
 * 
 * @author Eelco Hillenius
 */
public class AjaxNextButton extends AjaxWizardButton
{
	private static final long serialVersionUID = 1L;

	/**
	 * Construct.
	 * 
	 * @param id
	 * @param wizard
	 */
	public AjaxNextButton(String id, IAjaxWizard wizard)
	{
		super(id, wizard, "org.apache.wicket.extensions.wizard.next");
	}

	/**
	 * @see org.apache.wicket.Component#isEnabled()
	 */
	@Override
	public final boolean isEnabled()
	{
		return getWizardModel().isNextAvailable();
	}

	

	@Override
	protected void onClick(AjaxRequestTarget target, Form<?> form) {
		IWizardModel wizardModel = getWizardModel();
		IWizardStep step = wizardModel.getActiveStep();

		// let the step apply any state
		step.applyState();

		if(step instanceof IValidatableStep) {
			IValidatableStep validatableStep = (IValidatableStep)step;
			if(!validatableStep.isValid()) {
				AjaxWizard ajaxWizard = findParent(AjaxWizard.class);
				target.addComponent(ajaxWizard);
				return;
			}
		}
		// if the step completed after applying the state, move the
		// model onward
		if (step.isComplete())
		{
			wizardModel.next();
		}
		else
		{
			error(getLocalizer().getString(
				"org.apache.wicket.extensions.wizard.NextButton.step.did.not.complete", this));
		}
	}
	
	/**
	 * @see org.apache.wicket.Component#onBeforeRender()
	 */
	@Override
	protected final void onBeforeRender()
	{
		getForm().setDefaultButton(getLink());
		super.onBeforeRender();
	}
	
	@Override
	protected ResourceReference getImage() {
		if(isEnabled()) {
			return DefaultStyle.IMG_NEXT_ENABLED_PNG;
		} else {
			return DefaultStyle.IMG_NEXT_DISABLED_PNG;
		}
	}
}
