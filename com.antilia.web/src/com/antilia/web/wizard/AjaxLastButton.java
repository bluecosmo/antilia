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
 * Models a 'last' button in the wizard. When pressed, it calls {@link IWizardStep#applyState()} on
 * the active wizard step, and then moves to the last step in the model with
 * {@link IWizardModel#last()}.
 * 
 * @author Eelco Hillenius
 */
public class AjaxLastButton extends AjaxWizardButton
{
	private static final long serialVersionUID = 1L;

	/**
	 * Construct.
	 * 
	 * @param id
	 *            The component id
	 * @param wizard
	 *            The wizard
	 */
	public AjaxLastButton(String id, IAjaxWizard wizard)
	{
		super(id, wizard, "org.apache.wicket.extensions.wizard.last");
	}

	/**
	 * @see org.apache.wicket.Component#isEnabled()
	 */
	@Override
	public final boolean isEnabled()
	{
		return getWizardModel().isLastAvailable();
	}

	/**
	 * @see org.apache.wicket.Component#isVisible()
	 */
	@Override
	public final boolean isVisible()
	{
		return getWizardModel().isLastVisible();
	}
	
	@Override
	protected void onClick(AjaxRequestTarget target, Form<?> form) {
		IWizardModel wizardModel = getWizardModel();
		wizardModel.getActiveStep().applyState();
		wizardModel.last();
	}
	
	@Override
	protected ResourceReference getImage() {
		if(isEnabled())
			return DefaultStyle.IMG_LAST_ENABLED_PNG;
		return DefaultStyle.IMG_LAST_DISABLED_PNG;
	}
}
