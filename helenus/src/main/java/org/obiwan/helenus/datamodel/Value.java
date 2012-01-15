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
	
	public Value() {}
	
	
	public Value(T data) {
		
		this.timestamp = System.nanoTime();
	}
	
	/**
	 * @param data
	 * @param timestamp
	 */
	public Value(T data, long timestamp) {
		
		this.data = data;
		this.timestamp = timestamp;
	}
	
	private T data;
	private long timestamp = System.nanoTime();
	
	protected abstract byte[] toByteArray(T data);
	
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
