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
	@org.obiwan.helenus.core.annotation.ColumnFamily(familyName="users",columnNames={"name","age","email"},types={Type.STRING,Type.INTEGER,Type.STRING},superColumn=false)
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
			RowKey rowKey = new RowKey();
			Iterator<String> uidSet = columnFamily.getDataMap().keySet().iterator();
			
			String uid = "";
			while(uidSet.hasNext()){
				uid = uidSet.next();
			}
			boolean isSuperColumn = true;
			rowKey.setSelectedId(uid);
			Value[] values = new Value[3];
			values[0] = new Value<String>();
			values[0].setData("Daniele");
			values[1] = new Value<Integer>();
			values[1].setData(new Integer(21));
			values[2] = new Value<String>();
			values[2].setData("ddefrancesco@gmail.com");
			
			ColumnFamily _columnFamily = ColumnFamilyFactory.updateRowKey(columnFamily, rowKey, !isSuperColumn, values );		
			// write out some data
			Mutator mutator = Pelops.createMutator(connection.getPool());
			if(!_columnFamily.isSuperColumn()){
			//Giro sulla mappa
			Iterator<Map.Entry<String, Map<Integer, ColumnOrSuperColumn<org.obiwan.helenus.datamodel.Column>>>> iMap = _columnFamily.getDataMap().entrySet().iterator();
			while(iMap.hasNext()){
				
				Entry<String, Map<Integer, ColumnOrSuperColumn<org.obiwan.helenus.datamodel.Column>>> entry = iMap.next();
				String key = entry.getKey();
				
					Map<Integer, ColumnOrSuperColumn<org.obiwan.helenus.datamodel.Column>> records = entry.getValue();
					Iterator<Map.Entry<Integer, ColumnOrSuperColumn<org.obiwan.helenus.datamodel.Column>>> iCols = records.entrySet().iterator();
						while(iCols.hasNext()){
							Entry<Integer, ColumnOrSuperColumn<org.obiwan.helenus.datamodel.Column>> columnMap = iCols.next();
							@SuppressWarnings("unchecked")
							ColumnOrSuperColumn<org.obiwan.helenus.datamodel.Column> column = columnMap.getValue();
							System.out.println(""+column.getColumn().getValue().getData());
							Column thriftColumn = new Column();
							thriftColumn.setName(column.getColumn().getName().getBytes());
							byte[] _bArray = DataHandler.toByteArray(column.getColumn().getValue().getData());//FIXME
							thriftColumn.setValue(_bArray);
							thriftColumn.setTimestamp(column.getColumn().getValue().getTimestamp());
							mutator.writeColumn(columnFamily.getFamily(), key, thriftColumn);
							
						}					
				

			}
			}

			mutator.execute(ConsistencyLevel.ONE);
			
			
			
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
