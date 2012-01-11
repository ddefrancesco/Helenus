/**
 * 
 */
package org.obiwan.helenus.datamodel;

import java.io.Serializable;

/**
 * @author DeFrancescoD
 *
 */
public abstract class Value<T> implements Serializable{
	
	
	private static final long serialVersionUID = -878079677147948003L;
	private T data;
	private long timestamp = System.nanoTime();
	
	
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
