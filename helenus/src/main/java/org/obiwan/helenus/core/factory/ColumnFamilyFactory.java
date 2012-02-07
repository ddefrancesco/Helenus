/**
 * 
 */
package org.obiwan.helenus.core.factory;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.obiwan.helenus.datamodel.Column;
import org.obiwan.helenus.datamodel.ColumnFamily;
import org.obiwan.helenus.datamodel.ColumnOrSuperColumn;
import org.obiwan.helenus.datamodel.RowKey;
import org.obiwan.helenus.datamodel.Value;

/**
 * @author <a href="mailto:ddefrancesco@gmail.com">Daniele De Francesco</a> 
 *
 */
public class ColumnFamilyFactory implements Serializable {

	
	private static final long serialVersionUID = -1019797214874177140L;
	

	
	public static ColumnFamily createColumnFamily(String familyName, String[] columnNames,Value<?>[] valueClazzes, boolean isSuperColumn) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException{
		
		//Supporta l'annotation xké i parametri sono passati come array di tipi supportati, 
		//basta solo che allo stesso indice corrispondano nome e valori passati
		
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
				
				
//				Value<?> valClazz = null;
//				@SuppressWarnings("unchecked")
//				Constructor<?>[] constructors = (Constructor<?>[]) clazz.getDeclaredConstructors();
//				Class[] parameterTypes = new Class[4];
//				Constructor<?> _constructor = null;
//				
//				for(Constructor<?> constructor : constructors){
//					
//					parameterTypes = constructor.getParameterTypes();
//					for(int j = 0;j<parameterTypes.length;j++){
//						//System.out.println("Size di parameterTypes= "+parameterTypes.length);
//						if(parameterTypes.length == 1){
//							//_constructor = constructor;
//							_constructor = clazz.getConstructor(parameterTypes[j]);
//							
//							if(parameterTypes[j] == String.class){
//								
//								valClazz = new Value<String>() ;
//								valClazz.setData(new String("").getClass());
//								valClazz.setTimestamp(System.nanoTime());	
//								
//							}
//							else if(parameterTypes[j] == Long.TYPE){
//								
//								valClazz = new Value<Long>();
//								valClazz.setData(new Long(0).getClass());
//								valClazz.setTimestamp(System.nanoTime());	
//							}
//								
//							else if(parameterTypes[j] == Float.TYPE){
//								
//								valClazz = new Value<Float>();
//								valClazz.setData(new Float(0).getClass());
//								valClazz.setTimestamp(System.nanoTime());	
//							}
//							else if(parameterTypes[j] == Double.TYPE){
//								
//								valClazz = new Value<Double>();
//								valClazz.setData(new Double(0).getClass());
//								valClazz.setTimestamp(System.nanoTime());	
//							}
//							else if(parameterTypes[j] == Boolean.TYPE){
//								
//								valClazz = new Value<Boolean>();
//								valClazz.setData(new Boolean(false).getClass());
//								valClazz.setTimestamp(System.nanoTime());	
//							}
//							else if(parameterTypes[j] == Integer.TYPE){
//								
//								valClazz = new Value<Integer>();
//								valClazz.setData(new Integer(0).getClass());
//								valClazz.setTimestamp(System.nanoTime());	
//							}
//							else if (parameterTypes[j] == BigInteger.class) {
//
//								valClazz = new Value<BigInteger>();
//								valClazz.setData(new BigInteger("0").getClass());
//								valClazz.setTimestamp(System.nanoTime());
//							} 
//							else if (parameterTypes[j] == BigDecimal.class) {
//
//								valClazz = new Value<BigDecimal>();
//								valClazz.setData(new BigDecimal(0).getClass());
//								valClazz.setTimestamp(System.nanoTime());
//							}
//						
//							break;
//						}
//					
//					}

	//			}
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
}
