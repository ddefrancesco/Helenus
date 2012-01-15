/**
 * 
 */
package org.obiwan.helenus.datamodel;

/**
 * @author DeFrancescoD
 *
 */
public class NumericValue extends Value<Number> {

	private static final long serialVersionUID = -706346156620107118L;


	public NumericValue() {
		super();
		// TODO Auto-generated constructor stub
	}



	/**
	 * @param data
	 * @param timestamp
	 */
	public NumericValue(Number data, long timestamp) {
		super(data, timestamp);
		// TODO Auto-generated constructor stub
	}



	/**
	 * @param data
	 */
	public NumericValue(Number data) {
		super(data);
		// TODO Auto-generated constructor stub
	}



	@Override
	protected byte[] toByteArray(Number data) {
		// TODO Auto-generated method stub
		return null;
	}

}
