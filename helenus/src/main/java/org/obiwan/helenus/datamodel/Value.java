/**
 * 
 */
package org.obiwan.helenus.datamodel;

import java.io.Serializable;

/**
 * @author DeFrancescoD
 *
 */
public class Value<T> implements Serializable{
	
	private static final long serialVersionUID = -878079677147948003L;
	
	public Value() {}
	
	
	public Value(Class<?> data) {
		this.data = data;
		this.timestamp = System.nanoTime();
	}
	
	/**
	 * @param data
	 * @param timestamp
	 */
	public Value(Class<?> data, long timestamp) {
		
		this.data = data;
		this.timestamp = timestamp;
	}
	
	private Class<?> data;
	private long timestamp = System.nanoTime();
	
	protected byte[] toByteArray(T data){
		return null;
	}
	
	public Class<?> getData() {
		return data;
	}
	public void setData(Class<?> data) {
		this.data = data;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	
	
}
