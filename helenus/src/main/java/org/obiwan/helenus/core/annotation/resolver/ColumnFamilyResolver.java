/**
 * 
 */
package org.obiwan.helenus.core.annotation.resolver;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.obiwan.helenus.core.annotation.resolver.params.ColumnFamilyParams;
import org.obiwan.helenus.core.exception.AnnotationNotPresentException;

/**
 * @author DeFrancescoD
 *
 */
public class ColumnFamilyResolver {
	
	public  ColumnFamilyParams getColumnParams(Class<?> clazz) throws AnnotationNotPresentException{
		
		ColumnFamilyParams params = new ColumnFamilyParams(){};
		for(Method method : clazz.getMethods()){
			if(method.isAnnotationPresent(org.obiwan.helenus.core.annotation.ColumnFamily.class)) {
				Annotation[] annotations = method.getAnnotations();
				System.out.println("No. of annotations: " + annotations.length);
				for (Annotation annotation : annotations) {
					org.obiwan.helenus.core.annotation.ColumnFamily columnFamilyAnnotation = (org.obiwan.helenus.core.annotation.ColumnFamily)annotation; 
					params.setFamily(columnFamilyAnnotation.familyName());
					params.setColumnNames(columnFamilyAnnotation.columnNames());
					params.setTypes(columnFamilyAnnotation.types());
					params.setSuperColumn(columnFamilyAnnotation.superColumn());
				}			
			}/*else{
				throw new AnnotationNotPresentException();
			}*/
		}
		for(Field field : clazz.getFields()){
			if(field.isAnnotationPresent(org.obiwan.helenus.core.annotation.ColumnFamily.class)) {
				Annotation[] annotations = field.getAnnotations();
				//System.out.println("No. of annotations: " + annotations.length);
				for (Annotation annotation : annotations) {
					org.obiwan.helenus.core.annotation.ColumnFamily columnFamilyAnnotation = (org.obiwan.helenus.core.annotation.ColumnFamily)annotation; 
					params.setFamily(columnFamilyAnnotation.familyName());
					params.setColumnNames(columnFamilyAnnotation.columnNames());
					params.setTypes(columnFamilyAnnotation.types());
					params.setSuperColumn(columnFamilyAnnotation.superColumn());
				}			
			}
		}
		
		
		return params;
	}
}
