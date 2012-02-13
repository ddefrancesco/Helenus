/**
 * 
 */
package org.obiwan.helenus.core;

import java.lang.reflect.InvocationTargetException;

import org.obiwan.helenus.core.annotation.ConnectionData;
import org.obiwan.helenus.core.exception.AnnotationNotPresentException;
import org.obiwan.helenus.core.factory.ColumnFamilyFactory;
import org.obiwan.helenus.core.factory.ConnectionFactory;
import org.obiwan.helenus.datamodel.ColumnFamily;
import org.obiwan.helenus.datamodel.Value;

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
			String[] columnNames = {"name","age","email"};
			Value<?>[] valueTypes = new Value<?>[3];
			valueTypes[0] = new Value<String>();
			valueTypes[1] = new Value<Integer>();
			valueTypes[2] = new Value<String>();
			ColumnFamily<?> columnFamily = ColumnFamilyFactory.createColumnFamily(cf, columnNames, valueTypes, false);
			
			connection.shutdownConnectionPool();
		} catch (AnnotationNotPresentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
