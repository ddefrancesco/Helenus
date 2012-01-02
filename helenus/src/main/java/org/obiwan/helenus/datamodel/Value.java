/**
 * 
 */
package org.obiwan.helenus.datamodel;

import java.io.Serializable;

/**
 * @author DeFrancescoD
 *
 */
public class Value implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -878079677147948003L;
	private String data;
	private long timestamp = System.nanoTime();
	
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
