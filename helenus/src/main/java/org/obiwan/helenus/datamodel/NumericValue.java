/**
 * 
 */
package org.obiwan.helenus.datamodel;

import java.math.BigDecimal;
import java.math.BigInteger;



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
//		super(data, timestamp);
		// TODO Auto-generated constructor stub
	}



	/**
	 * @param data
	 */
	public NumericValue(Number data) {
//		super(data);
	}



	@Override
	public byte[] toByteArray(Number data) {
		String sBytes = null;
		byte[] bytes = new byte[4];
		if(data instanceof Integer){
			Integer iData = (Integer)data;
			//sBytes = ""+Integer.toHexString(iData);
			bytes = intToByteArray(iData);
		}
		
		if(data instanceof Long){
			
		}		

		if(data instanceof Float){
			
		}
		
		if(data instanceof BigInteger){
			
		}
		if(data instanceof BigDecimal){
			
		}
		return bytes;
	}
	private byte[] intToByteArray(int value) {
        return new byte[] {
                (byte)(value >>> 24),
                (byte)(value >>> 16),
                (byte)(value >>> 8),
                (byte)value};
}
}
