/**
 * 
 */
package org.obiwan.helenus.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author DeFrancescoD
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER,ElementType.TYPE})
public @interface ColumnFamily {
	
	public String familyName() default "name";
	public String[] columnNames() default {"col1","col2"};
	public Class<?>[] typeNames() default {String.class,String.class};
	public boolean superColumn() default false;
}
