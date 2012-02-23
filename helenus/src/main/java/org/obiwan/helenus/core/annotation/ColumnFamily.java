/**
 * 
 */
package org.obiwan.helenus.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.obiwan.helenus.core.enumeration.Type;

/**
 * @author <a href="mailto:ddefrancesco@gmail.com">Daniele De Francesco</a> 
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER,ElementType.TYPE})
public @interface ColumnFamily {
	
	public String familyName() default "name";
	public String[] columnNames() default {"col1","col2"};
	public Type[] types() default {Type.STRING,Type.STRING};
	public boolean superColumn() default false;
}
