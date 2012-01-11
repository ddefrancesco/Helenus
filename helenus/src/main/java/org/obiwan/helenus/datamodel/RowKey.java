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
	//TODO Constructor
	private byte[] uniqueKey;

	public byte[] getUniqueKey() {
		
		Random r = new Random(serialVersionUID);
		uniqueKey = new byte[32];
		r.nextBytes(uniqueKey);
		
		return uniqueKey;
	}
	
	
	
}
