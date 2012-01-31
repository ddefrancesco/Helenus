/**
 * 
 */
package org.obiwan.helenus.datamodel;

/**
 * @author <a href="mailto:ddefrancesco@gmail.com">Daniele De Francesco</a> 
 * 
 *
 */
public class NumericColumn extends GenericColumn<NumericValue> {
	private static final long serialVersionUID = 1517249370714486521L;
	
	public NumericColumn() {}
	
	public NumericColumn(String name) {
		this.setName(name);
		
	}

	public NumericColumn(String name,NumericValue value) {
		this.setName(name);
		this.value = value;
	}
	
	private NumericValue value;


	public NumericValue getValue() {
		return value;
	}

	public void setValue(NumericValue value) {
		this.value = value;
	}
	
	
	
}
