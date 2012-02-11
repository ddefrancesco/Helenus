/**
 * 
 */
package org.obiwan.helenus.core.annotation.test;

import org.obiwan.helenus.core.CassandraConnection;
import org.obiwan.helenus.core.annotation.ConnectionData;
import org.obiwan.helenus.core.exception.AnnotationNotPresentException;
import org.obiwan.helenus.core.factory.ConnectionFactory;

/**
 * @author ddefrancesco
 *
 */
@ConnectionData
public class HelenusTestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String cf = "users";
		
		try {
			CassandraConnection connection = ConnectionFactory.initInstance(HelenusTestMain.class);
			
			connection.shutdownConnectionPool();
		} catch (AnnotationNotPresentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
