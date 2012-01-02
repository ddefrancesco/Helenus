/**
 * 
 */
package org.obiwan.helenus.datamodel;


import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author DeFrancescoD
 *
 */
public class SuperColumn extends BaseColumn {

	
	private static final long serialVersionUID = 3563437367411206845L;
	
	
	private Map<byte[],Column> dataMap = new LinkedHashMap<byte[], Column>();

	public Map<byte[], Column> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<byte[], Column> dataMap) {
		this.dataMap = dataMap;
	}
	
	
}
