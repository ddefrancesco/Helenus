/**
 * 
 */
package org.obiwan.helenus.datamodel.intf;

import java.util.Map;

import org.obiwan.helenus.datamodel.ColumnOrSuperColumn;

/**
 * @author DeFrancescoD
 *
 */
public interface IColumnFamily {

	public String getFamily();
	
	public Map<String,Map<Integer,ColumnOrSuperColumn<?>>> getDataMap();
	
	public boolean isSuperColumn();
}
