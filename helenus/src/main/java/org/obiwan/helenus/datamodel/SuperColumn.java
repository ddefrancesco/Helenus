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

	
	/**
	 * 
	 */
	public SuperColumn() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 */
	public SuperColumn(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 3563437367411206845L;
	
	
	private Map<byte[],NumericColumn> dataMap = new LinkedHashMap<byte[], NumericColumn>();

	public Map<byte[], NumericColumn> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<byte[], NumericColumn> dataMap) {
		this.dataMap = dataMap;
	}
	
	
	
}
