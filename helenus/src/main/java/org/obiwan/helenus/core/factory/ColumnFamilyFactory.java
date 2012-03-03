/**
 * 
 */
package org.obiwan.helenus.core.factory;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.cassandra.thrift.ConsistencyLevel;
import org.obiwan.helenus.core.CassandraConnection;
import org.obiwan.helenus.core.annotation.resolver.ColumnFamilyResolver;
import org.obiwan.helenus.core.annotation.resolver.params.ColumnFamilyParams;
import org.obiwan.helenus.core.enumeration.Type;
import org.obiwan.helenus.core.exception.AnnotationNotPresentException;
import org.obiwan.helenus.datamodel.Column;
import org.obiwan.helenus.datamodel.ColumnFamily;
import org.obiwan.helenus.datamodel.ColumnOrSuperColumn;
import org.obiwan.helenus.datamodel.RowKey;
import org.obiwan.helenus.datamodel.Value;
import org.obiwan.helenus.util.DataHandler;
import org.scale7.cassandra.pelops.Mutator;
import org.scale7.cassandra.pelops.Pelops;

/**
 * @author <a href="mailto:ddefrancesco@gmail.com">Daniele De Francesco</a> 
 *
 */
public class ColumnFamilyFactory implements Serializable {

	
	private static final long serialVersionUID = -1019797214874177140L;
	
	//FIXME Deve supportare TUTTI I TIPI
	public static ColumnFamily createColumnFamily(String familyName, String[] columnNames,Type[] types, boolean isSuperColumn) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException{
		
		//Supporta l'annotation xké i parametri sono passati come array di tipi supportati, 
		//basta solo che allo stesso indice corrispondano nome e valori passati 
		Value[] valueClazzes = new Value<?>[columnNames.length];
		//for (int i =0 ; i < columnNames.length;i++){
		int i = 0;
			for(Type type : types){
				switch (type) {
				case STRING: 
						
					valueClazzes[i++] = new Value<String>("");
					break;
				case INTEGER: 
					
					valueClazzes[i++] = new Value<Integer>(new Integer(0));
					break;
				case DOUBLE: 
					
					valueClazzes[i++] = new Value<Double>(new Double(0));
					break;
				case FLOAT: 
					
					valueClazzes[i++] = new Value<Float>(new Float(0));
					break;
				case LONG: 
					
					valueClazzes[i++] = new Value<Long>(new Long(0));
					break;
				case SHORT: 
					
					valueClazzes[i++] = new Value<Short>(new Short("0"));
					break;				
				
				default:
					break;
				}
			}
			
		//}
		ColumnFamily columnFamily = createColumnFamily(familyName,columnNames,valueClazzes,isSuperColumn);
		
		
		return columnFamily;
	}
	
	
	public static ColumnFamily createColumnFamily(Class<?> clazz) throws AnnotationNotPresentException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException{
			
		ColumnFamilyResolver resolver = new ColumnFamilyResolver();
		ColumnFamilyParams params = resolver.getColumnParams(clazz);
		ColumnFamily columnFamily = createColumnFamily(params.getFamily(),params.getColumnNames(),params.getTypes(),params.isSuperColumn());
		
		return columnFamily;
	}
	
	public static ColumnFamily createColumnFamily(String familyName, String[] columnNames,Value<?>[] valueClazzes, boolean isSuperColumn) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException{
		
		ColumnFamily columnFamily = new ColumnFamily();
		columnFamily.setFamily(familyName);
		Map<String, Map<Integer, ColumnOrSuperColumn>> dataMap =
				new LinkedHashMap<String, Map<Integer,ColumnOrSuperColumn>>();
		
		RowKey uid = new RowKey();
		
		Map<Integer,ColumnOrSuperColumn> colMap = new LinkedHashMap<Integer, ColumnOrSuperColumn>();;
		Column col = null;
		for(int i = 0 ; i < columnNames.length; i++){
			if(!isSuperColumn){
				ColumnOrSuperColumn<Column> cos = new ColumnOrSuperColumn<Column>();
				
				String name = columnNames[i];
				Value<?> clazz = valueClazzes[i];
				
				

				for(Value<?> valClazz : valueClazzes){
					
					
					col = new Column(name,valClazz){};	
					cos.setColumn(col);
				}
				colMap.put(new Integer(i + 1), cos);
			}
			else
			{
				//cos = new ColumnOrSuperColumn();
				//TODO Parte relativa alle SuperColumn()
			}
			
			
			
		}
		
		dataMap.put(uid.toHexString(), colMap);
		
		columnFamily.setDataMap(dataMap);		
		
		
		return columnFamily;
	}

	public static ColumnFamily updateRowKey(ColumnFamily columnFamily, RowKey rowKey,boolean isSuperColumn, Value<?>[] values /*Mi mancano i parametri?*/) throws InstantiationException, IllegalAccessException{
		if(!isSuperColumn){
			Map<String,Map<Integer, ColumnOrSuperColumn<Column>>> _dataMap = columnFamily.getDataMap();
			if(_dataMap.containsKey(rowKey.getSelectedId())  ){
				//Caso update
				Map<Integer, ColumnOrSuperColumn<Column>>  columnMap  = _dataMap.get(rowKey.getSelectedId());
				Iterator<Map.Entry<Integer, ColumnOrSuperColumn<Column>>> mapIt = columnMap.entrySet().iterator();
				int i = 0;
				Map<Integer, ColumnOrSuperColumn<Column>>  _columnMap = new LinkedHashMap<Integer, ColumnOrSuperColumn<Column>>();
				while(mapIt.hasNext()){
				
					Entry<Integer, ColumnOrSuperColumn<Column>> entry = mapIt.next();
					ColumnOrSuperColumn<Column> colOrSup = entry.getValue();
					if(!isSuperColumn){//Column
						 Value<?> columnValue = values[i];
						 Column column = colOrSup.getColumn();

						 column.setValue(columnValue);
						 entry.getValue().setColumn(column);
					}
					else{//SuperColumn
						 
					}	
					i++;
					_columnMap.put(entry.getKey(), entry.getValue()) ;
				}
				_dataMap.remove(rowKey.getSelectedId());
				_dataMap.put(rowKey.getSelectedId(), _columnMap);
				columnFamily.setFamily(columnFamily.getFamily());
				columnFamily.setDataMap(_dataMap);
			}else{
				//TODO Caso rowkey null = insert
			}
		}
		columnFamily.setSuperColumn(isSuperColumn);
		return columnFamily;
	}
	
	private static void prepareColumns(ColumnFamily _columnFamily,
			Mutator mutator) {
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
						org.apache.cassandra.thrift.Column thriftColumn = new org.apache.cassandra.thrift.Column();
						thriftColumn.setName(column.getColumn().getName().getBytes());
						byte[] _bArray = DataHandler.toByteArray(column.getColumn().getValue().getData());//FIXME
						thriftColumn.setValue(_bArray);
						thriftColumn.setTimestamp(column.getColumn().getValue().getTimestamp());
						mutator.writeColumn(_columnFamily.getFamily(), key, thriftColumn);
						
					}					
			}
		}
	}
	
	public static void executeMutator(CassandraConnection connection,
			ColumnFamily _columnFamily) {
		Mutator mutator = Pelops.createMutator(connection.getPool());
		prepareColumns(_columnFamily, mutator);

		mutator.execute(ConsistencyLevel.ONE);
	}
}
