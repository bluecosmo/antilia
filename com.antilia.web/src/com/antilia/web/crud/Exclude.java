/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.crud;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation to mark a field as excluded...
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Exclude {
	
	CRUDMode[] types() default {CRUDMode.EDIT, CRUDMode.CREATE};
	
}
