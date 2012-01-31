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
	
	public RowKey(){
		uniqueKey = calcUniqueKey(); //byte[4] 32-bit
	}
	
	public RowKey(Integer uuid){
		int uid = uuid.intValue();
		uniqueKey = calcUniqueKey(toByteArray(uid)); //byte[4] 32-bit
		
	}
	private String selectedId;
	private byte[] uniqueKey = new byte[32];

	private byte[] calcUniqueKey() {
		
		Random r = new Random();
		byte[] _uniqueKey = new byte[32];
		r.nextBytes(_uniqueKey);
		
		return _uniqueKey;
	}
	
	
	private long calcLongUniqueKey() {
		
		Random r = new Random();
		long _uniqueKey;
		_uniqueKey = r.nextInt();
		
		return _uniqueKey;
	}
	
	public byte[] calcUniqueKey(byte[] bArray) {
		
		Random r = new Random();
		if(bArray != null)
			r.nextBytes(bArray);
		else	
			r.nextBytes(uniqueKey);
		
		return (bArray != null)?bArray:uniqueKey;
	}
	
	private byte[] toByteArray(int uid){
		byte[] _uniqueKey = new byte[32];
		
        for (int i = 0; i < 32; i++) {
            int offset = (_uniqueKey.length - 1 - i) * 8;
            _uniqueKey[i] = (byte) ((uid >>> offset) & 0xFF);
        }
		return _uniqueKey;
	}

	public byte[] getUniqueKey() {
		return uniqueKey;
	}

	public void setUniqueKey(byte[] uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
	
	@Override
	public String toString(){
		
		String hexString = Long.toHexString(calcLongUniqueKey());
		String to="";
		for(int i =0;i<hexString.length();i +=4){
			
			if(i == hexString.length()-4){
				to += hexString.substring(i,i+4);
				setSelectedId(to);
				break;
			}
			to += hexString.substring(i,i+4)+"-";	
		}
		 
		
		return to;
	}

	public String getSelectedId() {
		return selectedId;
	}

	public void setSelectedId(String selectedId) {
		this.selectedId = selectedId;
	}
}
