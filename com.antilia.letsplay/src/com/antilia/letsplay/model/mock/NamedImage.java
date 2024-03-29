/**
 * 
 */
package com.antilia.letsplay.model.mock;

import com.antilia.letsplay.model.ResourceImage;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class NamedImage extends ResourceImage {

	private static final long serialVersionUID = 1L;

	/**
	 * @param name
	 */
	public NamedImage(String name) {
		super(name+".jpg");
	}

}
