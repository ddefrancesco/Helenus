/**
 * 
 */
package org.obiwan.helenus.core;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.cassandra.thrift.Column;
import org.apache.cassandra.thrift.ConsistencyLevel;
import org.obiwan.helenus.core.CassandraConnection;
import org.obiwan.helenus.core.annotation.ConnectionData;
import org.obiwan.helenus.core.enumeration.Type;
import org.obiwan.helenus.core.exception.AnnotationNotPresentException;
import org.obiwan.helenus.core.factory.ColumnFamilyFactory;
import org.obiwan.helenus.core.factory.ConnectionFactory;
import org.obiwan.helenus.datamodel.ColumnFamily;
import org.obiwan.helenus.datamodel.ColumnOrSuperColumn;
import org.obiwan.helenus.datamodel.RowKey;
import org.obiwan.helenus.datamodel.Value;
import org.obiwan.helenus.util.DataHandler;
import org.scale7.cassandra.pelops.Mutator;
import org.scale7.cassandra.pelops.Pelops;
import org.scale7.cassandra.pelops.Selector;

/**
 * @author ddefrancesco
 *
 */
@ConnectionData
public class HelenusTestMain {
	
	
	/**
	 * @param args
	 */
	@SuppressWarnings("rawtypes")
	@org.obiwan.helenus.core.annotation.ColumnFamily(familyName="users",columnNames={"name","age"},types={Type.STRING,Type.INTEGER},superColumn=false)
	public static void main(String[] args) {
		
		String cf = "users";
		CassandraConnection connection;
		try {
			connection = ConnectionFactory.initInstance(HelenusTestMain.class);
			
			//Configurazione della ColumnFamily
//			String[] columnNames = new String[]{"name","age"};
//			Value[] valueClazzes = {new Value<String>(),new Value<Integer>()};
//			valueClazzes[0].setData("".getClass());
//			valueClazzes[1].setData(new Integer(0).getClass());
//			ColumnFamilyFactory factory = new ColumnFamilyFactory();
			
			ColumnFamily columnFamily = ColumnFamilyFactory.createColumnFamily(HelenusTestMain.class);
			
			RowKey rowKey = new RowKey();
			Iterator<String> uidSet = columnFamily.getDataMap().keySet().iterator();
			
			String uid = "";
			while(uidSet.hasNext()){
				uid = uidSet.next();
			}
			
			
			boolean isSuperColumn = false;
			rowKey.setSelectedId(uid);
			Value[] values = new Value[]{new Value<String>(),new Value<Integer>()};
			
			values[0].setData("Daniele");
			values[1].setData(new Integer(21));
			ColumnFamily _columnFamily = ColumnFamilyFactory.updateRowKey(columnFamily, rowKey, isSuperColumn, values );		

			//Population del db
			
			//String rowKey = "abc123";
		
		// write out some data
			ColumnFamilyFactory.executeMutator(connection, _columnFamily);
		
		
		
			// read back the data we just wrote
			org.scale7.cassandra.pelops.Selector selector = Pelops.createSelector(connection.getPool());
			List<Column> columns = new ArrayList<Column>();
			Column col1 =	selector.getColumnFromRow(columnFamily.getFamily(), rowKey.getSelectedId(), "name", ConsistencyLevel.ONE);
			columns.add(col1);
			Column col2 = 	selector.getColumnFromRow(columnFamily.getFamily(), rowKey.getSelectedId(), "age", ConsistencyLevel.ONE);
			columns.add(col2);
			//Becco ArrayOutOfBounds vedere gli inserimenti
			System.out.println("Name: " + Selector.getColumnStringValue(columns, "name"));
			System.out.println("Age: " + Selector.getColumnValue(columns, "age").toInt());
			
			// shut down the pool
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
