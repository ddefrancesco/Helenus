package org.obiwan.helenus.core.annotation.test;

import java.lang.annotation.Annotation;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.obiwan.helenus.core.annotation.ConnectionData;

@ConnectionData
public class ConnectionDataTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		Class[] classes = {this.getClass()};
		
		for (Class classObj : classes) {
			Annotation[] annotations = classObj.getAnnotations();
			System.out.println("No. of annotations: " + annotations.length);
			for (Annotation annotation : annotations) {
				ConnectionData connAnnotation = (ConnectionData)annotation; 
				System.out.println(connAnnotation.host());
				Assert.assertEquals("localhost", connAnnotation.host());
			}			
		}	
	}

}
