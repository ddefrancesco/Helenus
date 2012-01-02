/**
 * 
 */
package org.obiwan.helenus.datamodel;

import java.io.Serializable;
import java.util.Random;

/**
 * @author DeFrancescoD
 *
 */
public class RowKey implements Serializable{

	
	private static final long serialVersionUID = -7528517976875413759L;

	private byte[] uniqueKey;

	public byte[] getUniqueKey() {
		
		Random r = new Random(serialVersionUID);
		byte[] bytes = new byte[32];
		r.nextBytes(bytes);
		
		return bytes;
	}
	
	
	
}
