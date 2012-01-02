/**
 * 
 */
package org.obiwan.helenus.datamodel;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author DeFrancescoD
 *
 */
public class ColumnFamily implements Serializable {

	private static final long serialVersionUID = -7355373317699416400L;

	private Map<RowKey,ColumnOrSuperColumn<? extends BaseColumn>> dataMap = new LinkedHashMap<RowKey, ColumnOrSuperColumn<? extends BaseColumn>>();

	public Map<RowKey, ColumnOrSuperColumn<? extends BaseColumn>> getDataMap() {
		return dataMap;
	}

	public void setDataMap(
			Map<RowKey, ColumnOrSuperColumn<? extends BaseColumn>> dataMap) {
		this.dataMap = dataMap;
	}
	
	
}
