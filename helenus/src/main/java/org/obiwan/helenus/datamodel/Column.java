/**
 * 
 */
package org.obiwan.helenus.datamodel;

/**
 * @author DeFrancescoD
 *
 */
public class Column extends BaseColumn {
	private static final long serialVersionUID = 1517249370714486521L;
	
	public Column() {}
	
	public Column(String name) {
		this.name = name;
		
	}

	public Column(String name,Value value) {
		this.name = name;
		this.value = value;
	}
	
	private Value value;


	public Value getValue() {
		return value;
	}

	public void setValue(Value value) {
		this.value = value;
	}
	
	
	
}
