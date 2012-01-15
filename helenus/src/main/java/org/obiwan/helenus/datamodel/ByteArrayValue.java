/**
 * 
 */
package org.obiwan.helenus.datamodel;

/**
 * @author DeFrancescoD
 *
 */
public class ByteArrayValue extends Value<String> {

	private static final long serialVersionUID = 4007238145029446586L;
	
	public ByteArrayValue() {
		super();
		// TODO Auto-generated constructor stub
	}



	/**
	 * @param data
	 * @param timestamp
	 */
	public ByteArrayValue(String data, long timestamp) {
		super(data, timestamp);
		// TODO Auto-generated constructor stub
	}



	/**
	 * @param data
	 */
	public ByteArrayValue(String data) {
		super(data);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	protected byte[] toByteArray(String data) {
		return data.getBytes();
	}

}
