/**
 * 
 */
package org.obiwan.helenus.datamodel;

/**
 * @author DeFrancescoD
 *
 */
public class Column  {

	
	private static final long serialVersionUID = 1517249370714486521L;
	
	private String name;
	private Value<?> value;
	/**
	 * 
	 */
	public Column() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 * @param value
	 */
	public Column(String name, Value<?> value) {
		this.name = name;
		this.value = value;
	}

	/**
	 * @param name
	 */
	public Column(String name) {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Value<?> getValue() {
		return value;
	}

	public void setValue(Value<?> value) {
		this.value = value;
	}

	
}
