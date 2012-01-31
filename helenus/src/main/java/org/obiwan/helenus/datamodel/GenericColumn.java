/**
 * 
 */
package org.obiwan.helenus.datamodel;

import java.io.Serializable;

/**
 * @author DeFrancescoD
 *
 */
public abstract class GenericColumn<T> implements Serializable {

	
	private static final long serialVersionUID = -3717720903292693761L;
	protected String name;
	public GenericColumn() {}
	
	public GenericColumn(String name) {
		this.setName(name);
		
	}

	public GenericColumn(String name,T value) {
		this.setName(name);
		this.setValue(value);
	}
	
	

	public T getValue() {
		return getValue();
	}

	public void setValue(T value) {
		this.setValue(value);
	}
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
