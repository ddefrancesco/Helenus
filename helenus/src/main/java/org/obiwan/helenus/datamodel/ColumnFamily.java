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
	
	private String family;
	private Map<String,Map<Integer,ColumnOrSuperColumn>> dataMap = new LinkedHashMap<String, Map<Integer,ColumnOrSuperColumn>>();

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public Map<String,Map<Integer,ColumnOrSuperColumn>> getDataMap() {
		return dataMap;
	}

	public void setDataMap(
			Map<String,Map<Integer,ColumnOrSuperColumn>> dataMap) {
		this.dataMap = dataMap;
	}
	
	
}
