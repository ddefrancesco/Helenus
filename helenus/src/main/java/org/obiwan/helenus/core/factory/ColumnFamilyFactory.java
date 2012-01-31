/**
 * 
 */
package org.obiwan.helenus.core.factory;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

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
	

	
	public static ColumnFamily createColumnFamily(String familyName, String[] columnNames,Class<?>[] valueClazzes, boolean superColumn) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException{
		
		//Supporta l'annotation xké i parametri sono passati come array di tipi supportati, 
		//basta solo che allo stesso indice corrispondano nome e valori passati
		
		ColumnFamily columnFamily = new ColumnFamily();
		columnFamily.setFamily(familyName);
		Map<String, Map<Integer, ColumnOrSuperColumn>> dataMap =
				new LinkedHashMap<String, Map<Integer,ColumnOrSuperColumn>>();
		
		RowKey uid = new RowKey();
		ColumnOrSuperColumn cos = null;
		Map<Integer,ColumnOrSuperColumn> colMap = null;
		Column col = null;
		for(int i = 0 ; i < columnNames.length; i++){
			if(!superColumn){
				cos = new ColumnOrSuperColumn();
				
				String name = columnNames[i];
				Class<?> clazz = valueClazzes[i];
				Value<?> valClazz = null;
				@SuppressWarnings("unchecked")
				Constructor<?>[] constructors = (Constructor<?>[]) clazz.getDeclaredConstructors();
				Class<?>[] parameterTypes = null;
				Constructor<?> _constructor = null;
				
				for(Constructor<?> constructor : constructors){
					
					parameterTypes = constructor.getParameterTypes();
					for(int j = 0;j<parameterTypes.length;j++){
						System.out.println("Size di parameterTypes= "+parameterTypes.length);
						if(parameterTypes.length == 1){
							//_constructor = constructor;
							_constructor = clazz.getConstructor(parameterTypes[j]);
							
							if(parameterTypes[j] == String.class){
								
								valClazz = new Value<String>() ;
								valClazz.setData(new String("").getClass());
								valClazz.setTimestamp(System.nanoTime());	
								
							}
							else if(parameterTypes[j] == Long.TYPE){
								
								valClazz = new Value<Long>();
								valClazz.setData(new Long(0).getClass());
								valClazz.setTimestamp(System.nanoTime());	
							}
								
							else if(parameterTypes[j] == Float.TYPE){
								
								valClazz = new Value<Float>();
								valClazz.setData(new Float(0).getClass());
								valClazz.setTimestamp(System.nanoTime());	
							}
							else if(parameterTypes[j] == Double.TYPE){
								
								valClazz = new Value<Double>();
								valClazz.setData(new Double(0).getClass());
								valClazz.setTimestamp(System.nanoTime());	
							}
							else if(parameterTypes[j] == Boolean.TYPE){
								
								valClazz = new Value<Boolean>();
								valClazz.setData(new Boolean(false).getClass());
								valClazz.setTimestamp(System.nanoTime());	
							}
							else if(parameterTypes[j] == Integer.TYPE){
								
								valClazz = new Value<Integer>();
								valClazz.setData(new Integer(0).getClass());
								valClazz.setTimestamp(System.nanoTime());	
							}
						
							break;
						}
						
					}

				}
				
				col = new Column(name,valClazz);	
				cos.setColumn(col.getClass());
			}
			else
			{
				cos = new ColumnOrSuperColumn();
				//TODO Parte relativa alle SuperColumn()
			}
			
			colMap = new LinkedHashMap<Integer, ColumnOrSuperColumn>();
			colMap.put(new Integer(i + 1), cos);
		}
		
		dataMap.put(uid.toString(), colMap);
		
		columnFamily.setDataMap(dataMap);		
		
		
		return columnFamily;
	}

	public void insertAtRowKey(ColumnFamily columnFamily){
		
		//Map<RowKey,Map<Integer, ColumnOrSuperColumn>>
		
	}
}
