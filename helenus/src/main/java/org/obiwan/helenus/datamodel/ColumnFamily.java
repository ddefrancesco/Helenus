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
public class ColumnFamily<T> implements Serializable {

	private static final long serialVersionUID = -7355373317699416400L;
	
	private String family;
	private Map<String,Map<Integer,ColumnOrSuperColumn<?>>> dataMap = new LinkedHashMap<String, Map<Integer,ColumnOrSuperColumn<?>>>();
	private boolean superColumn = false;
	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public Map<String,Map<Integer,ColumnOrSuperColumn<?>>> getDataMap() {
		return dataMap;
	}

	public void setDataMap(
			Map<String,Map<Integer,ColumnOrSuperColumn<?>>> dataMap) {
		this.dataMap = dataMap;
	}

	public boolean isSuperColumn() {
		return superColumn;
	}

	public void setSuperColumn(boolean superColumn) {
		this.superColumn = superColumn;
	}
	
	
}
